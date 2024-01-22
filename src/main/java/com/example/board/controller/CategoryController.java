package com.example.board.controller;

import com.example.board.domain.Category;
import com.example.board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public Category insertCategory(@RequestBody Category category) throws SQLException {
        return categoryService.insertCategory(category);
    }
    @GetMapping
    public List<Category> getAllCategories() throws SQLException {
        return categoryService.getAllCategories();
    }
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Integer categoryId) throws SQLException {
        categoryService.deleteCategory(categoryId);
    }
}
