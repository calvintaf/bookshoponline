package zw.co.onlinebooks.bookshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import zw.co.onlinebooks.bookshop.exceptions.CategoryException;
import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.model.CategoryResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class CategoryServiceImplTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void createCategorySuccess() {
        Category category = new Category();
        category.setId(123L);
        category.setTitle("Science");

        Category category1 = new Category();
        category1.setId(123L);
        category1.setTitle("Science");
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findByTitle((String) any())).thenReturn(category1);
        assertThrows(CategoryException.class, () -> this.categoryServiceImpl.createCategory(new CategoryRequestDto("Science")));
        verify(this.categoryRepository).findByTitle((String) any());
    }

    @Test
    void createCategoryThrowsCategoryException() {
        when(this.categoryRepository.save((Category) any())).thenThrow(new CategoryException("Adding new Category: {}"));
        when(this.categoryRepository.findByTitle((String) any()))
                .thenThrow(new CategoryException("Adding new Category: {}"));
        assertThrows(CategoryException.class, () -> this.categoryServiceImpl.createCategory(new CategoryRequestDto("Science")));
        verify(this.categoryRepository).findByTitle((String) any());
    }

    @Test
    void getAllCategoriesSuccess() {
        when(this.modelMapper.map((Object) any(), (Class<CategoryResponseDto>) any()))
                .thenReturn(new CategoryResponseDto());

        Category category = new Category();
        category.setId(123L);
        category.setTitle("Science");

        Category category1 = new Category();
        category1.setId(123L);
        category1.setTitle("Science");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category);
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        assertEquals(2, this.categoryServiceImpl.getAllCategories().size());
        verify(this.modelMapper, atLeast(1)).map((Object) any(), (Class<CategoryResponseDto>) any());
        verify(this.categoryRepository).findAll();
    }

    @Test
    void getAllCategoriesThrowsCategoryException() {
        when(this.modelMapper.map((Object) any(), (Class<CategoryResponseDto>) any()))
                .thenThrow(new CategoryException("Getting all categories"));

        Category category = new Category();
        category.setId(123L);
        category.setTitle("Science");

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        assertThrows(CategoryException.class, () -> this.categoryServiceImpl.getAllCategories());
        verify(this.modelMapper).map((Object) any(), (Class<CategoryResponseDto>) any());
        verify(this.categoryRepository).findAll();
    }

    @Test
    void updateCategorySuccess() {
        Category category = new Category();
        category.setId(123L);
        category.setTitle("Science");

        Category category1 = new Category();
        category1.setId(123L);
        category1.setTitle("Science");
        when(this.categoryRepository.save((Category) any())).thenReturn(category1);
        when(this.categoryRepository.findCategoryById((Long) any())).thenReturn(category);
        CategoryResponseDto actualUpdateCategoryResult = this.categoryServiceImpl.updateCategory(123L, "Science");
        assertEquals(123L, actualUpdateCategoryResult.getId().longValue());
        assertEquals("Science", actualUpdateCategoryResult.getTitle());
        verify(this.categoryRepository).save((Category) any());
        verify(this.categoryRepository).findCategoryById((Long) any());
    }

    @Test
    void updateCategorySuccessThrowsCategoryException() {
        Category category = new Category();
        category.setId(123L);
        category.setTitle("Science");
        when(this.categoryRepository.save((Category) any())).thenThrow(new CategoryException("Updating Category: {}"));
        when(this.categoryRepository.findCategoryById((Long) any())).thenReturn(category);
        assertThrows(CategoryException.class, () -> this.categoryServiceImpl.updateCategory(123L, "Science"));
        verify(this.categoryRepository).save((Category) any());
        verify(this.categoryRepository).findCategoryById((Long) any());
    }

}
