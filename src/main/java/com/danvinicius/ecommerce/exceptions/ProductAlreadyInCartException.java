package com.danvinicius.ecommerce.exceptions;

public class ProductAlreadyInCartException extends RuntimeException {
    public ProductAlreadyInCartException() {
        super("Product already in cart");
    }
    public ProductAlreadyInCartException(String msg) {
        super(msg);
    }
}
