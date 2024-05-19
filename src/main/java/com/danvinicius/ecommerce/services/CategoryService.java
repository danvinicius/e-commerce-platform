package com.danvinicius.ecommerce.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
