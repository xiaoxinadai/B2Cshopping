package com.example.service;

import org.springframework.web.servlet.ModelAndView;

public interface ShoppingCartService {

    ModelAndView toShoppingCartView();

    void updateDataFromCart(Integer productCartId,Integer productAmount,Double totalPrice);

    void deleteByCartId(Integer productCartId);
}
