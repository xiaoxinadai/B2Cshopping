package com.example.service.impl;

import com.example.mapper.ProductMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;
    private ShoppingCartMapper shoppingCartMapper;

    public ProductServiceImpl(ProductMapper productMapper, ShoppingCartMapper shoppingCartMapper) {
        this.productMapper = productMapper;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Override
    public ModelAndView toProductDetailView(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        //根据id去查询相关信息
        Product productMessageById = productMapper.findMessageById(id);
        modelAndView.addObject("productMessageById",productMessageById);
        modelAndView.setViewName("detail");
        return modelAndView;
    }

    @Override
    public ModelAndView savaDataToCart(Integer productId,String productSpec,Integer amount,Double totalPrice) {
        ModelAndView modelAndView = new ModelAndView();
        //将信息保存到购物车
        shoppingCartMapper.savaMessageToCart(productId,productSpec,amount,totalPrice);
        modelAndView.setViewName("redirect:/shoppingCart/shoppingCart");
        return modelAndView;
    }
}
