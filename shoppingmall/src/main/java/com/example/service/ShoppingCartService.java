package com.example.service;

import com.example.dto.ShoppingCartDto;
import com.example.model.ShoppingCart;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ShoppingCartService {

    ModelAndView toShoppingCartView(HttpServletRequest httpServletRequest, List<ShoppingCartDto> shoppingCartList);

    void updateDataFromCart(Integer productCartId,Integer productAmount,Double totalPrice);

    void deleteByCartId(Integer productCartId);

    ModelAndView atOnceSettle(HttpSession httpSession, Integer[] shoppingCartArray, Double settlePrice);
}
