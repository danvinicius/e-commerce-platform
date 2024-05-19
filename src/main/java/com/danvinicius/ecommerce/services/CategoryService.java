package com.danvinicius.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.category.CategoryRequestDTO;
import com.danvinicius.ecommerce.entities.category.Category;
import com.danvinicius.ecommerce.exceptions.CategoryNotFoundException;
import com.danvinicius.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(String id) throws CategoryNotFoundException {
        return categoryRepository.findById(UUID.fromString(id)).orElseThrow(CategoryNotFoundException::new);
    }

      public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryRequestDTO data) {
        Category category = new Category(data);
        categoryRepository.save(category);
        return category;
    }

    public Category updateCategory(String id, CategoryRequestDTO data) {
        Category category = getCategoryById(id);
        if (data.name() != null && !data.name().isEmpty()) {
            category.setName(data.name());
        }
        categoryRepository.save(category);
        return category;
    }

    public void deleteCategory(String id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
