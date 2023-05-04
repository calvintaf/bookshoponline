package zw.co.onlinebooks.bookshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.exceptions.CategoryException;
import zw.co.onlinebooks.bookshop.model.BookRequestDto;
import zw.co.onlinebooks.bookshop.model.BookResponseDto;
import zw.co.onlinebooks.bookshop.model.CategoryResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.BookRepository;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;

@ContextConfiguration(classes = {BookServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ModelMapper modelMapper;

    private Long id = 123L;
    private String title = "Java";
    private String strPrice = "42";
    private Long price = 42L;
    private String description = "Best Description";

    @Test
    void createBookSuccess() {
        Category category = getCategory();
        when(this.categoryRepository.findCategoryById((Long) any())).thenReturn(category);

        Category category1 = getCategory();

        Book book = getBook1(category1);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book.setPrice(valueOfResult);

        when(this.bookRepository.save((Book) any())).thenReturn(book);
        BookResponseDto actualCreateBookResult = this.bookServiceImpl.createBook(new BookRequestDto());
        assertEquals(title, actualCreateBookResult.getTitle());
        assertEquals(description, actualCreateBookResult.getDescription());
        BigDecimal price = actualCreateBookResult.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(id, actualCreateBookResult.getId().longValue());
        assertEquals(strPrice, price.toString());
        CategoryResponseDto categoryResponseDto = actualCreateBookResult.getCategoryResponseDto();
        assertEquals(title, categoryResponseDto.getTitle());
        assertEquals(id, categoryResponseDto.getId().longValue());
        verify(this.categoryRepository).findCategoryById((Long) any());
        verify(this.bookRepository).save((Book) any());
    }

    private Book getBook1(Category category1) {
        Book book = new Book();
        book.setCategory(category1);
        book.setDescription(description);
        book.setId(id);
        book.setTitle(title);
        return book;
    }

    private Category getCategory() {
        Category category = new Category();
        category.setId(id);
        category.setTitle(title);
        return category;
    }

    @Test
    void createBookShouldThrowCategoryException() {
        Category category = getCategory();
        when(this.categoryRepository.findCategoryById((Long) any())).thenReturn(category);
        when(this.bookRepository.save((Book) any())).thenThrow(new CategoryException("Adding new Book: {}"));
        assertThrows(CategoryException.class, () -> this.bookServiceImpl.createBook(new BookRequestDto()));
        verify(this.categoryRepository).findCategoryById((Long) any());
        verify(this.bookRepository).save((Book) any());
    }

    @Test
    void updateBookSuccess() throws BookException {
        Category category = getCategory();
        when(this.categoryRepository.findCategoryById((Long) any())).thenReturn(category);

        Category category1 = getCategory();

        Book book = getBook1(category1);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book.setPrice(valueOfResult);

        Category category2 = getCategory();

        Book book1 = getBook1(category2);
        book1.setIsAvailable(true);
        book1.setPrice(BigDecimal.valueOf(price));
        when(this.bookRepository.save((Book) any())).thenReturn(book1);
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        BookResponseDto actualUpdateBookResult = this.bookServiceImpl.updateBook(id, new BookRequestDto());
        assertEquals(title, actualUpdateBookResult.getTitle());
        assertEquals(description, actualUpdateBookResult.getDescription());
        BigDecimal price = actualUpdateBookResult.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(id, actualUpdateBookResult.getId().longValue());
        assertEquals(strPrice, price.toString());
        CategoryResponseDto categoryResponseDto = actualUpdateBookResult.getCategoryResponseDto();
        assertEquals(title, categoryResponseDto.getTitle());
        assertEquals(id, categoryResponseDto.getId().longValue());
        verify(this.categoryRepository).findCategoryById((Long) any());
        verify(this.bookRepository).save((Book) any());
        verify(this.bookRepository).findBookById((Long) any());
    }

    @Test
    void updateBookThrowsCategoryException() throws BookException {
        Category category = getCategory();
        when(this.categoryRepository.findCategoryById((Long) any())).thenReturn(category);

        Category category1 = getCategory();

        Book book = getBook1(category1);
        book.setIsAvailable(true);
        book.setPrice(BigDecimal.valueOf(price));
        when(this.bookRepository.save((Book) any())).thenThrow(new CategoryException("foo"));
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        assertThrows(CategoryException.class, () -> this.bookServiceImpl.updateBook(id, new BookRequestDto()));
        verify(this.categoryRepository).findCategoryById((Long) any());
        verify(this.bookRepository).save((Book) any());
        verify(this.bookRepository).findBookById((Long) any());
    }

    @Test
    void removeBookSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book.setPrice(valueOfResult);

        Category category1 = getCategory();

        Book book1 = getBook1(category1);
        book1.setIsAvailable(true);
        book1.setPrice(BigDecimal.valueOf(price));
        when(this.bookRepository.save((Book) any())).thenReturn(book1);
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        BookResponseDto actualRemoveResult = this.bookServiceImpl.remove(id);
        assertEquals(title, actualRemoveResult.getTitle());
        assertEquals(description, actualRemoveResult.getDescription());
        BigDecimal price = actualRemoveResult.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(id, actualRemoveResult.getId().longValue());
        assertEquals(strPrice, price.toString());
        CategoryResponseDto categoryResponseDto = actualRemoveResult.getCategoryResponseDto();
        assertEquals(title, categoryResponseDto.getTitle());
        assertEquals(id, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).save((Book) any());
        verify(this.bookRepository).findBookById((Long) any());
    }

    @Test
    void removeBookThrowsCategoryException() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        book.setPrice(BigDecimal.valueOf(price));
        when(this.bookRepository.save((Book) any())).thenThrow(new CategoryException("foo"));
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        assertThrows(CategoryException.class, () -> this.bookServiceImpl.remove(id));
        verify(this.bookRepository).save((Book) any());
        verify(this.bookRepository).findBookById((Long) any());
    }

    @Test
    void getBookByIdSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book.setPrice(valueOfResult);
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        BookResponseDto actualBookById = this.bookServiceImpl.getBookById(id);
        assertEquals(title, actualBookById.getTitle());
        assertEquals(description, actualBookById.getDescription());
        BigDecimal price = actualBookById.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(id, actualBookById.getId().longValue());
        assertEquals(strPrice, price.toString());
        CategoryResponseDto categoryResponseDto = actualBookById.getCategoryResponseDto();
        assertEquals(title, categoryResponseDto.getTitle());
        assertEquals(id, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).findBookById((Long) any());
    }


    @Test
    void getAllBooksSuccess() {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setPrice(BigDecimal.valueOf(price));
        book.setIsAvailable(true);

        Category category1 = getCategory();

        Book book1 = getBook1(category1);
        book1.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book1.setPrice(valueOfResult);


        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);
        when(this.bookRepository.findAll()).thenReturn(bookList);
        List<BookResponseDto> actualAllBooks = this.bookServiceImpl.getAllBooks();
        assertEquals(2, actualAllBooks.size());
        BookResponseDto getResult = actualAllBooks.get(0);
        assertEquals(title, getResult.getTitle());
        BookResponseDto getResult1 = actualAllBooks.get(1);
        assertEquals(title, getResult1.getTitle());
        BigDecimal price = getResult1.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(id, getResult1.getId().longValue());
        assertEquals(id, getResult.getId().longValue());
        BigDecimal price1 = getResult.getPrice();
        assertEquals(price, price1);
        assertEquals(description, getResult.getDescription());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        CategoryResponseDto categoryResponseDto1 = getResult1.getCategoryResponseDto();
        assertEquals(categoryResponseDto, categoryResponseDto1);
        assertEquals(description, getResult1.getDescription());
        assertEquals(title, categoryResponseDto1.getTitle());
        assertEquals(id, categoryResponseDto1.getId().longValue());
        assertEquals(strPrice, price1.toString());
        assertEquals(id, categoryResponseDto.getId().longValue());
        assertEquals(strPrice, price.toString());
        assertEquals(title, categoryResponseDto.getTitle());
        verify(this.bookRepository).findAll();
    }

    @Test
    void getBooksByCategoryIdSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book.setPrice(valueOfResult);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(this.bookRepository.findAllByCategoryId((Long) any())).thenReturn(bookList);
        List<BookResponseDto> actualBooksByCategoryId = this.bookServiceImpl.getBooksByCategoryId(id);
        assertEquals(1, actualBooksByCategoryId.size());
        BookResponseDto getResult = actualBooksByCategoryId.get(0);
        assertEquals(title, getResult.getTitle());
        assertEquals(description, getResult.getDescription());
        BigDecimal price = getResult.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(id, getResult.getId().longValue());
        assertEquals(strPrice, price.toString());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        assertEquals(title, categoryResponseDto.getTitle());
        assertEquals(id, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).findAllByCategoryId((Long) any());
    }

    @Test
    void getBooksByCategoryIdThrowsCategoryException() throws BookException {
        when(this.bookRepository.findAllByCategoryId((Long) any())).thenThrow(new CategoryException("foo"));
        assertThrows(CategoryException.class, () -> this.bookServiceImpl.getBooksByCategoryId(id));
        verify(this.bookRepository).findAllByCategoryId((Long) any());
    }

    @Test
    void getMultipleBooksByCategoryIdSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        book.setPrice(BigDecimal.valueOf(price));

        Category category1 = getCategory();

        Book book1 = getBook1(category1);
        book1.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book1.setPrice(valueOfResult);


        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);
        when(this.bookRepository.findAllByCategoryId((Long) any())).thenReturn(bookList);
        List<BookResponseDto> actualBooksByCategoryId = this.bookServiceImpl.getBooksByCategoryId(id);
        assertEquals(2, actualBooksByCategoryId.size());
        BookResponseDto getResult = actualBooksByCategoryId.get(0);
        assertEquals(title, getResult.getTitle());
        BookResponseDto getResult1 = actualBooksByCategoryId.get(1);
        assertEquals(title, getResult1.getTitle());
        BigDecimal price = getResult1.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(id, getResult1.getId().longValue());
        assertEquals(id, getResult.getId().longValue());
        BigDecimal price1 = getResult.getPrice();
        assertEquals(price, price1);
        assertEquals(description, getResult.getDescription());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        CategoryResponseDto categoryResponseDto1 = getResult1.getCategoryResponseDto();
        assertEquals(categoryResponseDto, categoryResponseDto1);
        assertEquals(description, getResult1.getDescription());
        assertEquals(title, categoryResponseDto1.getTitle());
        assertEquals(id, categoryResponseDto1.getId().longValue());
        assertEquals(strPrice, price1.toString());
        assertEquals(id, categoryResponseDto.getId().longValue());
        assertEquals(strPrice, price.toString());
        assertEquals(title, categoryResponseDto.getTitle());
        verify(this.bookRepository).findAllByCategoryId((Long) any());
    }

    @Test
    void getAvailableBooksSuccess() {
        Category category = getCategory();

        Book book = getBook(category);
        BigDecimal valueOfResult = BigDecimal.valueOf(price);
        book.setPrice(valueOfResult);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(this.bookRepository.findAllByIsAvailable((Boolean) any())).thenReturn(bookList);
        List<BookResponseDto> actualAvailableBooks = this.bookServiceImpl.getAvailableBooks();
        assertEquals(1, actualAvailableBooks.size());
        BookResponseDto getResult = actualAvailableBooks.get(0);
        assertEquals(title, getResult.getTitle());
        assertEquals(description, getResult.getDescription());
        BigDecimal price = getResult.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(id, getResult.getId().longValue());
        assertEquals(strPrice, price.toString());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        assertEquals(title, categoryResponseDto.getTitle());
        assertEquals(id, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).findAllByIsAvailable((Boolean) any());
    }

    private Book getBook(Category category) {
        Book book = new Book();
        book.setCategory(category);
        book.setDescription(description);
        book.setId(id);
        book.setIsAvailable(true);
        book.setTitle(title);
        return book;
    }
}