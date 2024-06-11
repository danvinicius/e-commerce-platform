package com.danvinicius.ecommerce.dto.cart;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record CartRequestDTO(@NotNull List<CartItemRequestDTO> items) {
    
}
