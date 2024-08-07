package com.danvinicius.ecommerce.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartItemRequestDTO(@NotBlank String productId, @NotNull @Min(1) Integer quantity, @NotBlank String size) {
    
}
