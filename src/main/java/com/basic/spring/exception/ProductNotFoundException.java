package com.basic.spring.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Could not find product id: " + id);
    }

    public ProductNotFoundException(String name) {
        super("Could not find product name: " + name);
    }
}
