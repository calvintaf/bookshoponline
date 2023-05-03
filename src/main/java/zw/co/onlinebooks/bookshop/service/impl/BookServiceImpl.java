package zw.co.onlinebooks.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.exceptions.CategoryException;
import zw.co.onlinebooks.bookshop.model.BookRequestDto;
import zw.co.onlinebooks.bookshop.model.BookResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.BookRepository;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;
import zw.co.onlinebooks.bookshop.service.BookService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        log.info("Adding new Book: {}", bookRequestDto);

        Category category = categoryRepository.findCategoryById(bookRequestDto.getCategoryId());
        if (Objects.isNull(category)) {
            String message = "Category ID: " + bookRequestDto.getCategoryId() + " not found";
            log.info(message);
            throw new CategoryException(message);
        }

        Book book = new Book();
        book.setDescription(bookRequestDto.getDescription());
        book.setPrice(bookRequestDto.getPrice());
        book.setTitle(bookRequestDto.getTitle());
        book.setIsAvailable(true);
        book.setCategory(category);
        book = bookRepository.save(book);
        return new BookResponseDto(book);
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) throws BookException {
        Category category = categoryRepository.findCategoryById(bookRequestDto.getCategoryId());
        if (Objects.isNull(category)) {
            String message = "Category ID: " + bookRequestDto.getCategoryId() + " not found";
            log.info(message);
            throw new CategoryException(message);
        }

        Book updatedBook = bookRepository.findBookById(id);

        if (Objects.isNull(updatedBook)) {
            String message = "Book ID: " + id + " not found";
            log.info(message);
            throw new BookException(message);
        }

        updatedBook.setId(id);
        updatedBook.setCategory(category);
        updatedBook.setPrice(bookRequestDto.getPrice());
        updatedBook.setDescription(bookRequestDto.getDescription());
        updatedBook.setTitle(bookRequestDto.getTitle());
        updatedBook = bookRepository.save(updatedBook);
        return new BookResponseDto(updatedBook);
    }

    @Override
    public BookResponseDto remove(Long bookId) throws BookException {
        log.info("Removing book with ID : " + bookId);
        Book removedBook = bookRepository.findBookById(bookId);
        if (Objects.isNull(removedBook)) {
            String message = "Book ID: " + bookId + " not found";
            log.info(message);
            throw new BookException(message);
        }
        removedBook.setIsAvailable(false);
        removedBook = bookRepository.save(removedBook);
        return new BookResponseDto(removedBook);
    }

    @Override
    public BookResponseDto getBookById(Long bookId) throws BookException {
        log.info("Getting book with ID : " + bookId);
        Book book = bookRepository.findBookById(bookId);
        if (Objects.isNull(book)) {
            String message = "Book ID: " + bookId + " not found";
            log.info(message);
            throw new BookException(message);
        }
        return new BookResponseDto(book);
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        log.info("Getting all books");

        return bookRepository.findAll()
                .stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDto> getBooksByCategoryId(Long categoryId) throws BookException {
        log.info("Getting books with Category ID : " + categoryId);
        List<Book> books = bookRepository.findAllByCategoryId(categoryId);
        if (books.isEmpty()) {
            String message = "Books with Category ID: " + categoryId + " not found";
            log.info(message);
            throw new BookException(message);
        }
        return books.stream()
                .map(book -> modelMapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<BookResponseDto> getAvailableBooks() {
        log.info("Getting all available books");

        return bookRepository.findAllByIsAvailable(true)
                .stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }
}
