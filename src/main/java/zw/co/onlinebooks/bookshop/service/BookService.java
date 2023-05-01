package zw.co.onlinebooks.bookshop.service;

import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.model.BookRequestDto;
import zw.co.onlinebooks.bookshop.model.BookResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;

import java.util.List;

public interface BookService {
    BookResponseDto createBook(BookRequestDto bookRequestDto);

    BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) throws BookException;

    BookResponseDto remove(Long bookId) throws BookException;

    BookResponseDto getBookById(Long bookId) throws BookException;

    List<BookResponseDto> getAllBooks();

    List<BookResponseDto> getAvailableBooks();
}
