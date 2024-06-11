package com.danvinicius.ecommerce.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String msg) {
        super(msg);
    }
    public ForbiddenException() {
        super("Access denied");
    }
}
