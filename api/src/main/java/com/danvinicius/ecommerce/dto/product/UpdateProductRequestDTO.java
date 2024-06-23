package com.danvinicius.ecommerce.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequestDTO(
    @NotBlank String name,
    @NotBlank String description,
    String imageUrl,
    @NotNull BigDecimal price,
    @NotNull Integer quantity,
    Boolean recommendedOnMainPage,
    Double weight,
    Double discount
) {
    
}
