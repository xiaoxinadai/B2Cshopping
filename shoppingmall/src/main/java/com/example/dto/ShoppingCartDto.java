package com.example.dto;

import com.example.model.Product;
import com.example.model.ShoppingCart;
import lombok.Data;

@Data
public class ShoppingCartDto extends ShoppingCart {

    private Product product;
}
