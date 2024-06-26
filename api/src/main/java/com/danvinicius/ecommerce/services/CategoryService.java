package com.danvinicius.ecommerce.services;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.category.CategoryRequestDTO;
import com.danvinicius.ecommerce.entities.category.Category;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public Category getCategoryById(String id) throws ResourceNotFoundException {
        return categoryRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }

    public Category getCategoryBySlug(String slug) throws ResourceNotFoundException {
        List<Category> categories = this.getAllCategories();
        Category category = categories.stream().filter(c -> CategoryService.toSlug(c.getName()).equals(slug)).toList().get(0);
        return category;
    }

    public List<Category> getCategoriesById(Set<String> categoriesIds) throws ResourceNotFoundException {
        return categoryRepository.findAllById(categoriesIds.stream().map(id -> UUID.fromString(id)).toList());
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
        if (data.discount() != null && data.discount() > 0.0) {
            category.setDiscount(data.discount());
        }
        categoryRepository.save(category);
        return category;
    }

    public void deleteCategory(String id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
