package com.danvinicius.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danvinicius.ecommerce.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Page<Object[]> getBestSellingProducts(Pageable pageable) {
        return orderItemRepository.findBestSellingProducts(pageable);
    }
}
