package zw.co.onlinebooks.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.onlinebooks.bookshop.model.CategoryDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;
import zw.co.onlinebooks.bookshop.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        log.info("Adding new Category: {}",categoryDto);
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("Getting all categories");
        return categoryRepository.findAll();
    }
}
