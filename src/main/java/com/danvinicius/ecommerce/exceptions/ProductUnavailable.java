package com.danvinicius.ecommerce.exceptions;

public class ProductUnavailable extends RuntimeException {
    public ProductUnavailable() {
        super("Product unavailable");
    }
    public ProductUnavailable(String msg) {
        super(msg);
    }
}
