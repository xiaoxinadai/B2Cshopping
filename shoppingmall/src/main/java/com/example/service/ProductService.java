package com.example.service;

import org.springframework.web.servlet.ModelAndView;

public interface ProductService {

    ModelAndView toProductDetailView(Integer id);

    ModelAndView savaDataToCart(Integer productId,String productSpec,Integer amount,Double totalPrice);
}
