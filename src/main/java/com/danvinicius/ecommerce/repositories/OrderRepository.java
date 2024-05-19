package com.danvinicius.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danvinicius.ecommerce.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    
}
