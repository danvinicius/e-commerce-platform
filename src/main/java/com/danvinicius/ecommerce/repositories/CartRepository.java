package com.danvinicius.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danvinicius.ecommerce.entities.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    
}
