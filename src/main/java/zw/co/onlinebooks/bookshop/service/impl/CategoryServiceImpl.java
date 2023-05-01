package zw.co.onlinebooks.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.onlinebooks.bookshop.exceptions.CategoryException;
import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;
import zw.co.onlinebooks.bookshop.service.CategoryService;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryRequestDto categoryRequestDto) {
        log.info("Adding new Category: {}", categoryRequestDto);

        Category category = categoryRepository.findByTitle(categoryRequestDto.getTitle().toUpperCase(Locale.ROOT));
        if (!Objects.isNull(category)) {
            String message = "Category with Title: " + categoryRequestDto.getTitle().toUpperCase(Locale.ROOT) + " already exists";
            log.error(message);
            throw new CategoryException(message);
        }

        Category newCategory = new Category();
        newCategory.setTitle(categoryRequestDto.getTitle().toUpperCase(Locale.ROOT));
        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> getAllCategories() {
        log.info("Getting all categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long categoryId, String title) {
        log.info("Updating Category: {}", categoryId);

        Category updatedCategory = categoryRepository.findCategoryById(categoryId);
        if (Objects.isNull(updatedCategory)) {
            String message = "Category with ID : " + categoryId + " not found";
            log.error(message);
            throw new CategoryException(message);
        }

        updatedCategory.setTitle(title);
        updatedCategory.setId(categoryId);
        return categoryRepository.save(updatedCategory);
    }
}
