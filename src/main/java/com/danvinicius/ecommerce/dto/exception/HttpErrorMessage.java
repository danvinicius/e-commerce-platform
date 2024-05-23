package com.danvinicius.ecommerce.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HttpErrorMessage {
    private String message;
    private Integer status;
}
