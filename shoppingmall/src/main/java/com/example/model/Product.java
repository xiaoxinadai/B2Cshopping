package com.example.model;

import lombok.Data;

@Data
public class Product {

    private Integer id;         // 主键id

    private String productName;     // 商品名称

    private Double productPrice;    // 商品单价

    private String productDescription;      // 商品描述

    private String imgLocation;             //图片位置
}
