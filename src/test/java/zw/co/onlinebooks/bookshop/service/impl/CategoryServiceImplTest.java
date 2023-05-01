package zw.co.onlinebooks.bookshop.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.model.CategoryResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;

import java.util.List;

import static org.mockito.Mockito.*;

class CategoryServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ModelMapper modelMapper;
    @Mock
    Logger log;
    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {
        when(categoryRepository.findByTitle(anyString())).thenReturn(new Category());

        CategoryResponseDto result = categoryServiceImpl.createCategory(new CategoryRequestDto("title"));
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "title"), result);
    }

    @Test
    void testGetAllCategories() {
        List<CategoryResponseDto> result = categoryServiceImpl.getAllCategories();
        Assertions.assertEquals(List.of(new CategoryResponseDto(Long.valueOf(1), "title")), result);
    }

    @Test
    void testUpdateCategory() {
        when(categoryRepository.findCategoryById(anyLong())).thenReturn(new Category());

        CategoryResponseDto result = categoryServiceImpl.updateCategory(Long.valueOf(1), "title");
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "title"), result);
    }

    @Test
    void testGetCategoryById() {
        when(categoryRepository.findCategoryById(anyLong())).thenReturn(new Category());

        CategoryResponseDto result = categoryServiceImpl.getCategoryById(Long.valueOf(1));
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "title"), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme