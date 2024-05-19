package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(
    @NotBlank String name,
    @NotBlank String description,
    String imageUrl,
    @NotBlank BigDecimal price,
    @NotBlank String categoryId
    ) {
}
