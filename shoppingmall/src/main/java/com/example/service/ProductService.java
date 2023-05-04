package com.example.service;

import com.example.dto.ShoppingCartDto;
import com.example.model.ShoppingCart;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProductService {

    ModelAndView toProductDetailView(Integer id);

    ModelAndView saveDataToCart(Integer productId, String productSpec, Integer amount, Double totalPrice, List<ShoppingCartDto> shoppingCartListArray, HttpSession httpSession, HttpServletResponse httpServletResponse) throws JsonProcessingException;
}
