package com.danvinicius.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.product.ProductRequestDTO;
import com.danvinicius.ecommerce.entities.category.Category;
import com.danvinicius.ecommerce.entities.product.Product;
import com.danvinicius.ecommerce.exceptions.ProductNotFoundException;
import com.danvinicius.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product getProductById(String id) throws ProductNotFoundException {
        return productRepository.findById(UUID.fromString(id)).orElseThrow(ProductNotFoundException::new);
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequestDTO data) {
        Product product = new Product(data);
        Category category = categoryService.getCategoryById(data.categoryId());
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(String id, ProductRequestDTO data) {
        Product product = getProductById(id);
        if (data.name() != null && !data.name().isEmpty()) {
            product.setName(data.name());
        }
        if (data.description() != null && !data.description().isEmpty()) {
            product.setDescription(data.description());
        }
        if (data.imageUrl() != null && !data.imageUrl().isEmpty()) {
            product.setImageUrl(data.imageUrl());
        }
        if (data.price() != null) {
            product.setPrice(data.price());
        }
        if (data.categoryId() != null && !data.categoryId().isEmpty()) {
            Category category = categoryService.getCategoryById(data.categoryId());
            product.setCategory(category);
        }
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(String id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
    
}
