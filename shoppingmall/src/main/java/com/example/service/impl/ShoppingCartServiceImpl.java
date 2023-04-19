package com.example.service.impl;

import com.example.mapper.ShoppingCartMapper;
import com.example.model.ShoppingCart;
import com.example.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Override
    public ModelAndView toShoppingCartView() {
        ModelAndView modelAndView = new ModelAndView();
        List<ShoppingCart> shoppingCartMessageList = shoppingCartMapper.findShoppingCartMessage();
        modelAndView.addObject("shoppingCartMessageList",shoppingCartMessageList);
        modelAndView.setViewName("shoppingCart");
        return modelAndView;
    }
}
