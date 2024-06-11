package com.danvinicius.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danvinicius.ecommerce.entities.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    
}
