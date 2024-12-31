package com.springbootmicroservices.ProductService.service;

import com.springbootmicroservices.ProductService.entity.Product;
import com.springbootmicroservices.ProductService.model.ProductRequest;
import com.springbootmicroservices.ProductService.model.ProductResponse;

import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    List<ProductResponse> getAllProducts();

    void reduceQuantity(long productId, long quantity);
}
