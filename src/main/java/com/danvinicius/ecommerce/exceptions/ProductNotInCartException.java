package com.danvinicius.ecommerce.exceptions;

public class ProductNotInCartException extends RuntimeException {
    public ProductNotInCartException() {
        super("Product not in cart");
    }
    public ProductNotInCartException(String msg) {
        super(msg);
    }
}
