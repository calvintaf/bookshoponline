package zw.co.onlinebooks.bookshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.model.BookRequestDto;
import zw.co.onlinebooks.bookshop.model.BookResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponseDto createBook(@RequestBody BookRequestDto bookRequestDto) {
        return bookService.createBook(bookRequestDto);
    }

    @GetMapping("/{bookId}")
    public BookResponseDto getBook(@PathVariable Long bookId) throws BookException {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/all")
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/available")
    public List<BookResponseDto> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @PutMapping("/{bookId}")
    public BookResponseDto updateBook(@PathVariable Long bookId, @RequestBody BookRequestDto bookRequestDto) throws BookException {
        return bookService.updateBook(bookId, bookRequestDto);
    }

    @DeleteMapping("/{bookId}")
    public BookResponseDto removeBook(@PathVariable Long bookId) throws BookException {
        return bookService.remove(bookId);
    }
}
