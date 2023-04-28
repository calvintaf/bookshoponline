package zw.co.onlinebooks.bookshop.service;

import zw.co.onlinebooks.bookshop.model.CategoryDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
    Category updateCategory(CategoryDto categoryDto);
    List<Category> getAllCategories();
}
