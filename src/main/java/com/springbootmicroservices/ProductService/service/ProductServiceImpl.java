package com.springbootmicroservices.ProductService.service;

import com.springbootmicroservices.ProductService.entity.Product;
import com.springbootmicroservices.ProductService.exception.ProductServiceCustomException;
import com.springbootmicroservices.ProductService.model.ProductRequest;
import com.springbootmicroservices.ProductService.model.ProductResponse;
import com.springbootmicroservices.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product.......");

        Product product = Product.builder()
                .productName(productRequest.getName())
                .productPrice(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();

        productRepository.save(product);
        log.info("Successfully added product.......");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Getting product by id {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product not found","E9000"));

        ProductResponse productResponse = new ProductResponse();

        copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.info("Getting all products");
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products.stream()
                .map(this::convertToProductResponse)
                .collect(Collectors.toList());

        return productResponses;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity for product id {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product not found","E9000"));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException("Insufficient quantity","INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Successfully reduced quantity for product id {}", productId);
    }

    private ProductResponse convertToProductResponse(Product product) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(product.getProductId());
        productResponse.setProductName(product.getProductName());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setQuantity(product.getQuantity());

        return productResponse;
    }
}
