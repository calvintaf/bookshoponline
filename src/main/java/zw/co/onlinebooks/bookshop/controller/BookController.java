package zw.co.onlinebooks.bookshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import zw.co.onlinebooks.bookshop.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto) {
        return new ResponseEntity<>(bookService.createBook(bookRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long bookId) throws BookException {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BookResponseDto>> getBooksByCategoryId(@PathVariable Long categoryId) throws BookException {
        return new ResponseEntity<>(bookService.getBooksByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookResponseDto>> getAvailableBooks() {
        return new ResponseEntity<>(bookService.getAvailableBooks(), HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long bookId, @RequestBody BookRequestDto bookRequestDto) throws BookException {
        return new ResponseEntity<>(bookService.updateBook(bookId, bookRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> removeBook(@PathVariable Long bookId) throws BookException {
        return new ResponseEntity<>(bookService.remove(bookId), HttpStatus.OK);
    }
}
