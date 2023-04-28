package zw.co.onlinebooks.bookshop.service;

import zw.co.onlinebooks.bookshop.model.CategoryDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
}
