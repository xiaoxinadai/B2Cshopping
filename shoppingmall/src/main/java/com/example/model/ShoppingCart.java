package com.example.model;

import lombok.Data;

@Data
public class ShoppingCart {

    private Integer id;

    private Integer productId;

    private String productSpec;

    private Integer productAmount;

    private Double productTotalPrice;
}
