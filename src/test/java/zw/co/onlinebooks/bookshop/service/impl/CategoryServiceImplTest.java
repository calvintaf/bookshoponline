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
import java.util.Optional;

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

    CategoryRequestDto categoryRequestDto;

    CategoryResponseDto categoryResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

  /*  @Test
    void saveTimesheetShouldReturnBadResponseWhenMobileNumberIsEmpty(){
        timeSheetRequest.setMobileNumber("");
        CustomerResponse customerResponse = timeSheetService.saveTimeSheet(timeSheetRequest);
        assertEquals(RequestResponse.getBADResponse().getResult(),customerResponse.getResult());
    }*/


    @Test
    void saveCategoryShouldReturnOkResponseWhenCalledWithCorrectParams(){
        when(categoryRepository.findByTitle(anyString())).thenReturn(new Category());//(Optional.empty());
        when(categoryRepository.save(any())).thenReturn(categoryRepository);
        CategoryResponseDto categoryResponseDto = categoryServiceImpl.createCategory(categoryRequestDto);
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "Java"), categoryResponseDto);
        //assertEquals(RequestResponse.getOKResponse().getResult(),customerResponse.getResult());
    }
    @Test
    void testCreateCategory() {
        when(categoryRepository.findByTitle(anyString())).thenReturn(null);

        CategoryResponseDto result = categoryServiceImpl.createCategory(new CategoryRequestDto("Java"));
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "Java"), result);
    }

    @Test
    void testCreateCategory_ol() {
        when(categoryRepository.findByTitle(anyString())).thenReturn(new Category());

        CategoryResponseDto result = categoryServiceImpl.createCategory(new CategoryRequestDto("Java"));
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "Java"), result);
    }

  /*  @Test
    void testGetAllCategories() {
        when(categoryRepository.findByTitle(anyString())).thenReturn(new List<Category>());
        List<CategoryResponseDto> result = categoryServiceImpl.getAllCategories();
        Assertions.assertEquals(List.of(new CategoryResponseDto(Long.valueOf(1), "Java")), result);
    }*/

    @Test
    void testUpdateCategory() {
        when(categoryRepository.findCategoryById(anyLong())).thenReturn(new Category());

        CategoryResponseDto result = categoryServiceImpl.updateCategory(Long.valueOf(1), "Java");
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "Java"), result);
    }

    @Test
    void testGetCategoryById() {
        when(categoryRepository.findCategoryById(anyLong())).thenReturn(new Category());

        CategoryResponseDto result = categoryServiceImpl.getCategoryById(Long.valueOf(1));
        Assertions.assertEquals(new CategoryResponseDto(Long.valueOf(1), "Java"), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme