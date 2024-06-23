package com.danvinicius.ecommerce.dto.category;

import java.util.UUID;

import com.danvinicius.ecommerce.entities.category.Category;

import lombok.Getter;

@Getter
public class CategoryDTO {
    private UUID id;
    private String name;
    private Double discount;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.discount = category.getDiscount();
    }
}