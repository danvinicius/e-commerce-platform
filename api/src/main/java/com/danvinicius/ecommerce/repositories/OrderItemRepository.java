package com.danvinicius.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.danvinicius.ecommerce.entities.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Query("SELECT oi.product.id, SUM(oi.quantity) AS totalQuantity FROM OrderItem oi GROUP BY oi.product.id ORDER BY totalQuantity DESC")
    Page<Object[]> findBestSellingProducts(Pageable pageable);
}
