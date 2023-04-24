package com.example.mapper;

import com.example.dto.ShoppingCartDto;
import com.example.model.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    @Transactional
    void savaMessageToCart(Integer productId,String productSpec,Integer amount,Double totalPrice);

    List<ShoppingCartDto> findShoppingCartMessage();
    @Transactional
    void updateDataFromCart(Integer productCartId, Integer productAmount, Double totalPrice);

    @Transactional
    void deleteByCartId(Integer productCartId);

    //根据购物车id查找商品id
    Integer findProductIdByCartId(Integer cartId);

    //根据购物车id查询该id下的所有信息
    ShoppingCart findAllMessageByCartId(Integer cartId);
}
