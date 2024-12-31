package com.springbootmicroservices.ProductService.model;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private long price;
    private long quantity;
}
