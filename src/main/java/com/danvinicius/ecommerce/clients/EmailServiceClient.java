package com.danvinicius.ecommerce.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.danvinicius.ecommerce.dto.email.EmailRequestDTO;

import jakarta.validation.Valid;

@FeignClient(name = "${email.service.name}", url = "${email.service.url}")
public interface EmailServiceClient {
    
    @PostMapping
    void sendEmail(@Valid @RequestBody EmailRequestDTO emailRequest);
}
