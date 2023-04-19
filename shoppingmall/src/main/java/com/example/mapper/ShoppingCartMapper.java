package com.example.mapper;

import com.example.model.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    void savaMessageToCart(Integer productId,String productSpec,Integer amount);

    List<ShoppingCart> findShoppingCartMessage();
}
