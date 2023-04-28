package zw.co.onlinebooks.bookshop.service;

import zw.co.onlinebooks.bookshop.model.BookDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDto bookDto);
    List<Book> getAllBooks();
}
