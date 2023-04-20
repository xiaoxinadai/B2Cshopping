package com.example.mapper;

import com.example.dto.ShoppingCartDto;
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
}
