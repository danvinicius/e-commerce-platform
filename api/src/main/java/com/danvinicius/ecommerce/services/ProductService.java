package com.danvinicius.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.dto.product.CreateProductRequestDTO;
import com.danvinicius.ecommerce.dto.product.UpdateProductRequestDTO;
import com.danvinicius.ecommerce.entities.category.Category;
import com.danvinicius.ecommerce.entities.product.Product;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderItemService orderItemService;

    public Product getProductById(String id) throws ResourceNotFoundException {
        return productRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }
    
    public List<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(
            PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())
            .withSort(Sort.by("updatedAt")
            .reverse()))
            .getContent();
    }

    public List<Product> getBestSellingProducts(Pageable pageable) {
        Page<Object[]> results = orderItemService.getBestSellingProducts(pageable);

        List<Product> bestSellingProducts = new ArrayList<Product>();
        for (Object[] result : results) {
            UUID productId = (UUID) result[0];
            bestSellingProducts.add(this.getProductById(productId.toString()));
        }
        return bestSellingProducts;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product createProduct(CreateProductRequestDTO data) {
        Product product = new Product(data);
        List<Category> categories = categoryService.getCategoriesById(data.categoriesIds());
        for (Category category: categories) {
            product.getCategories().add(category);
        }
        product.setStock(data.stock());
        productRepository.save(product);
        return product;
    }

    public Product addCategoryToProduct(String productId, String categoryId) {
        Product product = getProductById(productId);
        Category category = categoryService.getCategoryById(categoryId);
        product.getCategories().add(category);
        productRepository.save(product);
        return product;
    }

    public Product removeCategoryFromProduct(String productId, String categoryId) {
        Product product = getProductById(productId);
        Category category = categoryService.getCategoryById(categoryId);
        product.getCategories().remove(category);
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(String id, UpdateProductRequestDTO data) {
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
        if (data.stock() != null) {
            product.setStock(data.stock());
        }
        if (data.discount() != null) {
            product.setDiscount(data.discount());
        }
        if (data.weight() != null) {
            product.setWeight(data.weight());
        }
        if (data.recommendedOnMainPage() != null) {
            product.setRecommendedOnMainPage(data.recommendedOnMainPage());
        }
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(String id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
    
}
