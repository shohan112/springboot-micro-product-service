package com.springbootmicroservices.ProductService.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException {
    private String ErrorCode;
    public ProductServiceCustomException(String message, String ErrorCode) {
        super(message);
        this.ErrorCode = ErrorCode;
    }
}
