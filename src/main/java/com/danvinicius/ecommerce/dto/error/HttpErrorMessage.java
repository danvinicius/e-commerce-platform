package com.danvinicius.ecommerce.dto.error;

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
