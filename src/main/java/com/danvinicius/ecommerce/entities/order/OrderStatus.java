package com.danvinicius.ecommerce.entities.order;

public enum OrderStatus {
    WAITING_PAYMENT("waiting_payment"),
    PAID("paid"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
