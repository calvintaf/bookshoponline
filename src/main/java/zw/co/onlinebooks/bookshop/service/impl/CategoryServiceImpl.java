package zw.co.onlinebooks.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.exceptions.CategoryException;
import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.model.CategoryResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.persistance.repo.CategoryRepository;
import zw.co.onlinebooks.bookshop.service.CategoryService;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        log.info("Adding new Category: {}", categoryRequestDto);

        Category category = categoryRepository.findByTitle(categoryRequestDto.getTitle().toUpperCase(Locale.ROOT));
        if (!Objects.isNull(category)) {
            String message = "Category with Title: " + categoryRequestDto.getTitle().toUpperCase(Locale.ROOT) + " already exists";
            log.error(message);
            throw new CategoryException(message);
        }

            Category newCategory = new Category();
            newCategory.setTitle(categoryRequestDto.getTitle().toUpperCase(Locale.ROOT));
            newCategory = categoryRepository.save(newCategory);
            return new CategoryResponseDto(newCategory);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        log.info("Getting all categories");

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, String title) {
        log.info("Updating Category: {}", categoryId);

        Category updatedCategory = categoryRepository.findCategoryById(categoryId);
        if (Objects.isNull(updatedCategory)) {
            String message = "Category with ID : " + categoryId + " not found";
            log.error(message);
            throw new CategoryException(message);
        }

        updatedCategory.setTitle(title);
        updatedCategory.setId(categoryId);
        updatedCategory = categoryRepository.save(updatedCategory);
        return new CategoryResponseDto(updatedCategory);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        log.info("Getting category with ID : " + categoryRepository);
        Category category = categoryRepository.findCategoryById(categoryId);
        if (Objects.isNull(category)) {
            String message = "Category ID: " + categoryId + " not found";
            log.info(message);
            throw new CategoryException(message);
        }
        return new CategoryResponseDto(categoryRepository.getReferenceById(categoryId));
    }
}
