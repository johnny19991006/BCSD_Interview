package com.example.board.service;

import com.example.board.domain.Category;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface CategoryService {
    public Category insertCategory(Category category);
    public List<Category> getAllCategories();
    public void deleteCategory(Integer categoryId) throws EmptyResultDataAccessException;
}
