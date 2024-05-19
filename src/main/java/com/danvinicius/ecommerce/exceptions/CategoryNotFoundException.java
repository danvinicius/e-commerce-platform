package com.danvinicius.ecommerce.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("Category not found");
    }

    public CategoryNotFoundException(String msg) {
        super(msg);
    }
}
