package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
    @NotBlank String name,
    @NotBlank String description,
    String imageUrl,
    @NotNull BigDecimal price,
    @NotNull Integer quantity,
    @NotBlank String categoryId
    ) {
}
