package com.danvinicius.ecommerce.config.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.danvinicius.ecommerce.dto.exception.HttpErrorMessage;
import com.danvinicius.ecommerce.exceptions.BadRequestException;
import com.danvinicius.ecommerce.exceptions.ProductAlreadyInCartException;
import com.danvinicius.ecommerce.exceptions.ProductNotInCartException;
import com.danvinicius.ecommerce.exceptions.ProductUnavailableException;
import com.danvinicius.ecommerce.exceptions.ResourceNotFoundException;
import com.danvinicius.ecommerce.exceptions.ForbiddenException;

@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<HttpErrorMessage> resourceNotFoundHandler(ResourceNotFoundException exception) {
        Integer status = HttpStatus.NOT_FOUND.value();
        HttpErrorMessage errorMessage = new HttpErrorMessage(exception.getMessage(), status);
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(ProductAlreadyInCartException.class)
    private ResponseEntity<HttpErrorMessage> productAlreadyInCartHandler(ProductAlreadyInCartException exception) {
        Integer status = HttpStatus.BAD_REQUEST.value();
        HttpErrorMessage errorMessage = new HttpErrorMessage(exception.getMessage(), status);
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(ProductNotInCartException.class)
    private ResponseEntity<HttpErrorMessage> productNotInCartHandler(ProductNotInCartException exception) {
        Integer status = HttpStatus.BAD_REQUEST.value();
        HttpErrorMessage errorMessage = new HttpErrorMessage(exception.getMessage(), status);
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(ProductUnavailableException.class)
    private ResponseEntity<HttpErrorMessage> productUnavailableHandler(ProductUnavailableException exception) {
        Integer status = HttpStatus.CONFLICT.value();
        HttpErrorMessage errorMessage = new HttpErrorMessage(exception.getMessage(), status);
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(ForbiddenException.class)
    private ResponseEntity<HttpErrorMessage> forbiddenExceptionHandler(ForbiddenException exception) {
        Integer status = HttpStatus.FORBIDDEN.value();
        HttpErrorMessage errorMessage = new HttpErrorMessage(exception.getMessage(), status);
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<HttpErrorMessage> badRequestHandler(BadRequestException exception) {
        Integer status = HttpStatus.BAD_REQUEST.value();
        HttpErrorMessage errorMessage = new HttpErrorMessage(exception.getMessage(), status);
        return ResponseEntity.status(status).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<HttpErrorMessage> runtimeExceptionHandler(RuntimeException exception) {
        Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        HttpErrorMessage errorMessage = new HttpErrorMessage(exception.getMessage(), status);
        return ResponseEntity.status(status).body(errorMessage);
    }
}
