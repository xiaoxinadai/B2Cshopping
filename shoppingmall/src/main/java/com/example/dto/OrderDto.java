package com.example.dto;

import com.example.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends Order {

    private List<ShoppingCartDto> shoppingCartDtoList;
}
