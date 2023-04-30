package zw.co.onlinebooks.bookshop.service;

import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.model.BookDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDto bookDto);

    Book updateBook(Long id, BookDto bookDto) throws BookException;

    Book remove(Long bookId) throws BookException;

    Book getBook(Long bookId) throws BookException;

    List<Book> getAllBooks();

    List<Book> getAvailableBooks();
}
