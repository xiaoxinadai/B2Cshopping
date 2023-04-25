package com.example.model;

import lombok.Data;

@Data
public class Order {

    private Integer id;

    private String productCartId;

    private Double settlePrice;
}
