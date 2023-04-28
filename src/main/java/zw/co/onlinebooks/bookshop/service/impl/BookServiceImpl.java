package zw.co.onlinebooks.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.onlinebooks.bookshop.model.BookDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.repo.BookRepository;
import zw.co.onlinebooks.bookshop.service.BookService;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book createBook(BookDto bookDto) {
        log.info("Adding new Book: {}",bookDto);
        Book book = new Book();
        book.setDescription(book.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setTitle(bookDto.getTitle());
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        log.info("Getting all books");
        return bookRepository.findAll();
    }
}
