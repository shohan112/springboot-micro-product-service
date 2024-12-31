package com.springbootmicroservices.ProductService.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private long productId;
    private String productName;
    private long productPrice;
    private long quantity;

}
