package com.danvinicius.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danvinicius.ecommerce.entities.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}
