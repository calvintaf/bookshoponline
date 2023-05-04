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

    @Test
    void createBookSuccess() {
        Category category = getCategory();
        when(this.categoryRepository.findCategoryById((Long) any())).thenReturn(category);

        Category category1 = getCategory();

        Book book = getBook1(category1);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book.setPrice(valueOfResult);

        when(this.bookRepository.save((Book) any())).thenReturn(book);
        BookResponseDto actualCreateBookResult = this.bookServiceImpl.createBook(new BookRequestDto());
        assertEquals("Java", actualCreateBookResult.getTitle());
        assertEquals("Best Description", actualCreateBookResult.getDescription());
        BigDecimal price = actualCreateBookResult.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(123L, actualCreateBookResult.getId().longValue());
        assertEquals("42", price.toString());
        CategoryResponseDto categoryResponseDto = actualCreateBookResult.getCategoryResponseDto();
        assertEquals("Java", categoryResponseDto.getTitle());
        assertEquals(123L, categoryResponseDto.getId().longValue());
        verify(this.categoryRepository).findCategoryById((Long) any());
        verify(this.bookRepository).save((Book) any());
    }

    private Book getBook1(Category category1) {
        Book book = new Book();
        book.setCategory(category1);
        book.setDescription("Best Description");
        book.setId(123L);
        book.setTitle("Java");
        return book;
    }

    private Category getCategory() {
        Category category = new Category();
        category.setId(123L);
        category.setTitle("Java");
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
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book.setPrice(valueOfResult);

        Category category2 = getCategory();

        Book book1 = getBook1(category2);
        book1.setIsAvailable(true);
        book1.setPrice(BigDecimal.valueOf(42L));
        when(this.bookRepository.save((Book) any())).thenReturn(book1);
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        BookResponseDto actualUpdateBookResult = this.bookServiceImpl.updateBook(123L, new BookRequestDto());
        assertEquals("Java", actualUpdateBookResult.getTitle());
        assertEquals("Best Description", actualUpdateBookResult.getDescription());
        BigDecimal price = actualUpdateBookResult.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(123L, actualUpdateBookResult.getId().longValue());
        assertEquals("42", price.toString());
        CategoryResponseDto categoryResponseDto = actualUpdateBookResult.getCategoryResponseDto();
        assertEquals("Java", categoryResponseDto.getTitle());
        assertEquals(123L, categoryResponseDto.getId().longValue());
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
        book.setPrice(BigDecimal.valueOf(42L));
        when(this.bookRepository.save((Book) any())).thenThrow(new CategoryException("foo"));
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        assertThrows(CategoryException.class, () -> this.bookServiceImpl.updateBook(123L, new BookRequestDto()));
        verify(this.categoryRepository).findCategoryById((Long) any());
        verify(this.bookRepository).save((Book) any());
        verify(this.bookRepository).findBookById((Long) any());
    }

    @Test
    void removeBookSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book.setPrice(valueOfResult);

        Category category1 = getCategory();

        Book book1 = getBook1(category1);
        book1.setIsAvailable(true);
        book1.setPrice(BigDecimal.valueOf(42L));
        when(this.bookRepository.save((Book) any())).thenReturn(book1);
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        BookResponseDto actualRemoveResult = this.bookServiceImpl.remove(123L);
        assertEquals("Java", actualRemoveResult.getTitle());
        assertEquals("Best Description", actualRemoveResult.getDescription());
        BigDecimal price = actualRemoveResult.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(123L, actualRemoveResult.getId().longValue());
        assertEquals("42", price.toString());
        CategoryResponseDto categoryResponseDto = actualRemoveResult.getCategoryResponseDto();
        assertEquals("Java", categoryResponseDto.getTitle());
        assertEquals(123L, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).save((Book) any());
        verify(this.bookRepository).findBookById((Long) any());
    }

    @Test
    void removeBookThrowsCategoryException() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        book.setPrice(BigDecimal.valueOf(42L));
        when(this.bookRepository.save((Book) any())).thenThrow(new CategoryException("foo"));
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        assertThrows(CategoryException.class, () -> this.bookServiceImpl.remove(123L));
        verify(this.bookRepository).save((Book) any());
        verify(this.bookRepository).findBookById((Long) any());
    }

    @Test
    void getBookByIdSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book.setPrice(valueOfResult);
        when(this.bookRepository.findBookById((Long) any())).thenReturn(book);
        BookResponseDto actualBookById = this.bookServiceImpl.getBookById(123L);
        assertEquals("Java", actualBookById.getTitle());
        assertEquals("Best Description", actualBookById.getDescription());
        BigDecimal price = actualBookById.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(123L, actualBookById.getId().longValue());
        assertEquals("42", price.toString());
        CategoryResponseDto categoryResponseDto = actualBookById.getCategoryResponseDto();
        assertEquals("Java", categoryResponseDto.getTitle());
        assertEquals(123L, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).findBookById((Long) any());
    }


    @Test
    void getAllBooksSuccess() {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setPrice(BigDecimal.valueOf(42L));
        book.setIsAvailable(true);

        Category category1 = getCategory();

        Book book1 = getBook1(category1);
        book1.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book1.setPrice(valueOfResult);


        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);
        when(this.bookRepository.findAll()).thenReturn(bookList);
        List<BookResponseDto> actualAllBooks = this.bookServiceImpl.getAllBooks();
        assertEquals(2, actualAllBooks.size());
        BookResponseDto getResult = actualAllBooks.get(0);
        assertEquals("Java", getResult.getTitle());
        BookResponseDto getResult1 = actualAllBooks.get(1);
        assertEquals("Java", getResult1.getTitle());
        BigDecimal price = getResult1.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals(123L, getResult.getId().longValue());
        BigDecimal price1 = getResult.getPrice();
        assertEquals(price, price1);
        assertEquals("Best Description", getResult.getDescription());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        CategoryResponseDto categoryResponseDto1 = getResult1.getCategoryResponseDto();
        assertEquals(categoryResponseDto, categoryResponseDto1);
        assertEquals("Best Description", getResult1.getDescription());
        assertEquals("Java", categoryResponseDto1.getTitle());
        assertEquals(123L, categoryResponseDto1.getId().longValue());
        assertEquals("42", price1.toString());
        assertEquals(123L, categoryResponseDto.getId().longValue());
        assertEquals("42", price.toString());
        assertEquals("Java", categoryResponseDto.getTitle());
        verify(this.bookRepository).findAll();
    }

    @Test
    void getBooksByCategoryIdSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book.setPrice(valueOfResult);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(this.bookRepository.findAllByCategoryId((Long) any())).thenReturn(bookList);
        List<BookResponseDto> actualBooksByCategoryId = this.bookServiceImpl.getBooksByCategoryId(123L);
        assertEquals(1, actualBooksByCategoryId.size());
        BookResponseDto getResult = actualBooksByCategoryId.get(0);
        assertEquals("Java", getResult.getTitle());
        assertEquals("Best Description", getResult.getDescription());
        BigDecimal price = getResult.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("42", price.toString());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        assertEquals("Java", categoryResponseDto.getTitle());
        assertEquals(123L, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).findAllByCategoryId((Long) any());
    }

    @Test
    void getBooksByCategoryIdThrowsCategoryException() throws BookException {
        when(this.bookRepository.findAllByCategoryId((Long) any())).thenThrow(new CategoryException("foo"));
        assertThrows(CategoryException.class, () -> this.bookServiceImpl.getBooksByCategoryId(123L));
        verify(this.bookRepository).findAllByCategoryId((Long) any());
    }

    @Test
    void getMultipleBooksByCategoryIdSuccess() throws BookException {
        Category category = getCategory();

        Book book = getBook1(category);
        book.setIsAvailable(true);
        book.setPrice(BigDecimal.valueOf(42L));

        Category category1 = getCategory();

        Book book1 = getBook1(category1);
        book1.setIsAvailable(true);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book1.setPrice(valueOfResult);


        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book);
        when(this.bookRepository.findAllByCategoryId((Long) any())).thenReturn(bookList);
        List<BookResponseDto> actualBooksByCategoryId = this.bookServiceImpl.getBooksByCategoryId(123L);
        assertEquals(2, actualBooksByCategoryId.size());
        BookResponseDto getResult = actualBooksByCategoryId.get(0);
        assertEquals("Java", getResult.getTitle());
        BookResponseDto getResult1 = actualBooksByCategoryId.get(1);
        assertEquals("Java", getResult1.getTitle());
        BigDecimal price = getResult1.getPrice();
        assertEquals(valueOfResult, price);
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals(123L, getResult.getId().longValue());
        BigDecimal price1 = getResult.getPrice();
        assertEquals(price, price1);
        assertEquals("Best Description", getResult.getDescription());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        CategoryResponseDto categoryResponseDto1 = getResult1.getCategoryResponseDto();
        assertEquals(categoryResponseDto, categoryResponseDto1);
        assertEquals("Best Description", getResult1.getDescription());
        assertEquals("Java", categoryResponseDto1.getTitle());
        assertEquals(123L, categoryResponseDto1.getId().longValue());
        assertEquals("42", price1.toString());
        assertEquals(123L, categoryResponseDto.getId().longValue());
        assertEquals("42", price.toString());
        assertEquals("Java", categoryResponseDto.getTitle());
        verify(this.bookRepository).findAllByCategoryId((Long) any());
    }

    @Test
    void getAvailableBooksSuccess() {
        Category category = getCategory();

        Book book = getBook(category);
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book.setPrice(valueOfResult);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(this.bookRepository.findAllByIsAvailable((Boolean) any())).thenReturn(bookList);
        List<BookResponseDto> actualAvailableBooks = this.bookServiceImpl.getAvailableBooks();
        assertEquals(1, actualAvailableBooks.size());
        BookResponseDto getResult = actualAvailableBooks.get(0);
        assertEquals("Java", getResult.getTitle());
        assertEquals("Best Description", getResult.getDescription());
        BigDecimal price = getResult.getPrice();
        assertSame(valueOfResult, price);
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("42", price.toString());
        CategoryResponseDto categoryResponseDto = getResult.getCategoryResponseDto();
        assertEquals("Java", categoryResponseDto.getTitle());
        assertEquals(123L, categoryResponseDto.getId().longValue());
        verify(this.bookRepository).findAllByIsAvailable((Boolean) any());
    }

    private Book getBook(Category category) {
        Book book = new Book();
        book.setCategory(category);
        book.setDescription("Best Description");
        book.setId(123L);
        book.setIsAvailable(true);
        book.setTitle("Java");
        return book;
    }


}