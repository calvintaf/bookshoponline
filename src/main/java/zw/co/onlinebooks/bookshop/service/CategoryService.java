package zw.co.onlinebooks.bookshop.service;

import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryRequestDto categoryRequestDto);

    List<Category> getAllCategories();

    Category updateCategory(Long categoryId, String title);
}
