package com.example.board.service;

import com.example.board.domain.Category;
import com.example.board.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.categoryRepository = repository;
    }
    @Override
    public Category insertCategory(Category category) { // 카테고리 추가
        return categoryRepository.save(category);
    }
    @Override
    public List<Category> getAllCategories() { // 카테고리 전체조회 (id 오름차순)
        return categoryRepository.findAllByOrderByCategoryIdAsc();
    }
    @Override
    public void deleteCategory(Integer categoryId) throws EmptyResultDataAccessException { // 카테고리 삭제
        categoryRepository.deleteById(categoryId);
    }
}
