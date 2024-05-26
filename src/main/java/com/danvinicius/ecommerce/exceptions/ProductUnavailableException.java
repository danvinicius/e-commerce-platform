package com.danvinicius.ecommerce.exceptions;

public class ProductUnavailableException extends RuntimeException {
    public ProductUnavailableException() {
        super("Product unavailable");
    }
    public ProductUnavailableException(String msg) {
        super(msg);
    }
}
