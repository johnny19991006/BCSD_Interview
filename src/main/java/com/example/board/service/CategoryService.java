package com.example.board.service;

import com.example.board.domain.Category;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    public Category insertCategory(Category category) throws Exception;
    public List<Category> getAllCategories();
    public void deleteCategory(Integer categoryId) throws EmptyResultDataAccessException;
}
