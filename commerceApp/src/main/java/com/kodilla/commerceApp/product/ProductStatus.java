package com.kodilla.commerceApp.product;

public enum ProductStatus {
    SOLD("Sold"),
    RESERVATION("Product is reserved"),
    IN_STOCK("Available in stock");

    private final String status;

    ProductStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}