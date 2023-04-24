package com.example.model;

import lombok.Data;
import org.springframework.boot.json.JacksonJsonParser;

@Data
public class Order {

    private Integer id;

    private String productCartId;

    private Double settlePrice;
}
