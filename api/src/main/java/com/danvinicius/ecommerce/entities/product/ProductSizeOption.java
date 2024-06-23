package com.danvinicius.ecommerce.entities.product;

public enum ProductSizeOption {
    EXTRA_SMALL("PP"),
    SMALL("P"),
    MEDIUM("M"),
    LARGE("G"),
    EXTRA_LARGE("GG"),
    EXTRA_EXTRA_LARGE("XGG");

    private String size;

    ProductSizeOption(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

}
