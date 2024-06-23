package com.danvinicius.ecommerce.dto.product;

import com.danvinicius.ecommerce.entities.product.ProductSize;

import lombok.Getter;

@Getter
public class ProductSizeDTO {
    private String size;

    public ProductSizeDTO(ProductSize productSize) {
        this.size = productSize.getSize();
    }
}
