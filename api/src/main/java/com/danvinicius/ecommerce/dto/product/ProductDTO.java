package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.danvinicius.ecommerce.dto.category.CategoryDTO;
import com.danvinicius.ecommerce.entities.product.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String imageUrl;
    private List<CategoryDTO> categories;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.imageUrl = product.getImageUrl();
        this.categories = product.getCategories().stream()
                                .map(CategoryDTO::new)
                                .collect(Collectors.toList());
    }
}
