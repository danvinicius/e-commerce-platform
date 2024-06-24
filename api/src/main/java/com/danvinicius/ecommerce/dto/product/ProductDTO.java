package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.danvinicius.ecommerce.dto.category.CategoryDTO;
import com.danvinicius.ecommerce.entities.product.Product;

import lombok.Getter;

@Getter
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private List<CategoryDTO> categories;
    private Map<String, Integer> stock;
    private Boolean recommendedOnMainPage;
    private Double weight;
    private Double discount;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.recommendedOnMainPage = product.getRecommendedOnMainPage();
        this.weight = product.getWeight();
        this.discount = product.getDiscount();
        this.stock = product.getStock().entrySet().stream()
            .filter(entry -> entry.getValue() > 0)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        this.categories = product.getCategories().stream()
                                .map(CategoryDTO::new)
                                .collect(Collectors.toList());
    }
}
