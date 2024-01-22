package com.example.board.service;

import com.example.board.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    public Category insertCategory(Category category) throws SQLException;
    public List<Category> getAllCategories() throws SQLException;
    public void deleteCategory(Integer categoryId) throws SQLException;
}
