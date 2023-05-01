package zw.co.onlinebooks.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.exceptions.CategoryException;
import zw.co.onlinebooks.bookshop.model.BookRequestDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.BookRepository;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;
import zw.co.onlinebooks.bookshop.service.BookService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Book createBook(BookRequestDto bookRequestDto) {
        /*log.info("Adding new Book: {}", bookDto);

        Category category = categoryRepository.findCategoryById(bookDto.getCategoryId());
        if (Objects.isNull(category)) {
            String message = "Category ID: " + bookDto.getCategoryId() + " not found";
            log.info(message);
            throw new CategoryException(message);
        }

        Book book = new Book();
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setTitle(bookDto.getTitle());
        book.setCategory(category);
        return bookRepository.save(book);*/

            // Create a new Book object with the given details
            Book book = new Book();
            book.setTitle(bookRequestDto.getTitle());
            book.setDescription(bookRequestDto.getDescription());
            book.setPrice(bookRequestDto.getPrice());

            // Find the Category entity with the given category ID (assuming you have a CategoryRepository)
            Category category = categoryRepository.findById(bookRequestDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
            book.setCategory(category);

            // Save the new book to the database
            return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookRequestDto bookRequestDto) throws BookException {
        Category category = categoryRepository.findCategoryById(bookRequestDto.getCategoryId());
        if (Objects.isNull(category)) {
            String message = "Category ID: " + bookRequestDto.getCategoryId() + " not found";
            log.info(message);
            throw new CategoryException(message);
        }

        Book updatedBook = bookRepository.getReferenceById(id);

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
        return bookRepository.save(updatedBook);
    }

    @Override
    public Book remove(Long bookId) throws BookException {
        log.info("Removing book with ID : " + bookId);
        Book removedBook = bookRepository.getReferenceById(bookId);
        if (Objects.isNull(removedBook)) {
            String message = "Book ID: " + bookId + " not found";
            log.info(message);
            throw new BookException(message);
        }
        removedBook.setIs_Available(false);
        return bookRepository.save(removedBook);
    }

    @Override
    public Book getBook(Long bookId) throws BookException {
        log.info("Getting book with ID : " + bookId);
        Book book = bookRepository.getReferenceById(bookId);
        if (Objects.isNull(book)) {
            String message = "Book ID: " + bookId + " not found";
            log.info(message);
            throw new BookException(message);
        }
        return bookRepository.getReferenceById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        log.info("Getting all books");
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAvailableBooks() {
        log.info("Getting all available books");
        return bookRepository.findAllByIsAvailable(true);
    }
}
