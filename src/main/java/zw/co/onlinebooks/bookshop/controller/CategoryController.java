package zw.co.onlinebooks.bookshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.onlinebooks.bookshop.exceptions.BookException;
import zw.co.onlinebooks.bookshop.exceptions.CategoryException;
import zw.co.onlinebooks.bookshop.model.CategoryRequestDto;
import zw.co.onlinebooks.bookshop.model.CategoryResponseDto;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;
import zw.co.onlinebooks.bookshop.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryRequestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long categoryId) throws CategoryException {
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @PutMapping("/categoryId")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, categoryRequestDto.getTitle()), HttpStatus.OK);
    }
}
