package com.example.demo.exception;

public class ProductsNotFoundException extends Throwable {
    public ProductsNotFoundException(String message) {
        super(message);
    }
}
