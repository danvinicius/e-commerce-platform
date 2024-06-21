package com.danvinicius.ecommerce.dto.category;

import java.util.UUID;

import com.danvinicius.ecommerce.entities.category.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private UUID id;
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}