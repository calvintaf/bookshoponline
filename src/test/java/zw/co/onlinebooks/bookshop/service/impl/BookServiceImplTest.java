package zw.co.onlinebooks.bookshop.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.model.BookRequestDto;
import zw.co.onlinebooks.bookshop.model.BookResponseDto;
import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.model.CategoryResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.BookRepository;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    Logger log;

    @InjectMocks
    BookServiceImpl bookServiceImpl;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() {
        when(categoryRepository.findCategoryById(anyLong())).thenReturn(new Category());

        BookResponseDto result = bookServiceImpl.createBook(new BookRequestDto("description", "title", new BigDecimal(0), Long.valueOf(1)));
        Assertions.assertEquals(new BookResponseDto(Long.valueOf(1), "description", "title", new BigDecimal(0), new CategoryResponseDto(Long.valueOf(1), "title")), result);
    }


    @Test
    void testUpdateBook() throws BookException {
        when(bookRepository.findBookById(anyLong())).thenReturn(new Book());
        when(categoryRepository.findCategoryById(anyLong())).thenReturn(new Category());

        BookResponseDto result = bookServiceImpl.updateBook(Long.valueOf(1), new BookRequestDto("description", "title", new BigDecimal(0), Long.valueOf(1)));
        Assertions.assertEquals(new BookResponseDto(Long.valueOf(1), "description", "title", new BigDecimal(0), new CategoryResponseDto(Long.valueOf(1), "title")), result);
    }

    @Test
    void testRemove() throws BookException {
        when(bookRepository.findBookById(anyLong())).thenReturn(new Book());

        BookResponseDto result = bookServiceImpl.remove(Long.valueOf(1));
        Assertions.assertEquals(new BookResponseDto(Long.valueOf(1), "description", "title", new BigDecimal(0), new CategoryResponseDto(Long.valueOf(1), "title")), result);
    }

    @Test
    void testGetBookById() throws BookException {
        when(bookRepository.findBookById(anyLong())).thenReturn(new Book());

        BookResponseDto result = bookServiceImpl.getBookById(Long.valueOf(1));
        Assertions.assertEquals(new BookResponseDto(Long.valueOf(1), "description", "title", new BigDecimal(0), new CategoryResponseDto(Long.valueOf(1), "title")), result);
    }

    @Test
    void testGetAllBooks() {
        List<BookResponseDto> result = bookServiceImpl.getAllBooks();
        Assertions.assertEquals(List.of(new BookResponseDto(Long.valueOf(1), "description", "title", new BigDecimal(0), new CategoryResponseDto(Long.valueOf(1), "title"))), result);
    }

    @Test
    void testGetAvailableBooks() {
        when(bookRepository.findAllByIsAvailable(anyBoolean())).thenReturn(List.of(new Book()));

        List<BookResponseDto> result = bookServiceImpl.getAvailableBooks();
        Assertions.assertEquals(List.of(new BookResponseDto(Long.valueOf(1), "description", "title", new BigDecimal(0), new CategoryResponseDto(Long.valueOf(1), "title"))), result);
    }
}