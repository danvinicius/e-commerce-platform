package com.danvinicius.ecommerce.config.email.content;

public class RegisterUserEmailContent {

    public static String getSubject() {
        String subject = "Welcome to our e-commerce platform!";
        return subject;
    }

    public static String getBody(String firstName) {
        String body = "Hey, " + firstName + "! Your registration was successful on our e-commerce platform. Hope you enjoy this experience :)";
        return body;
    }
}
