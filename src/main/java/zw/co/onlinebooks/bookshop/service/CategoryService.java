package zw.co.onlinebooks.bookshop.service;

import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.model.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto updateCategory(Long categoryId, String title);

    CategoryResponseDto getCategoryById(Long categoryId);
}
