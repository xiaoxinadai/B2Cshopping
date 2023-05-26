package com.example.service.impl;

import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ModelAndView deleteProduct(Integer productId) {
        ModelAndView modelAndView = new ModelAndView();
        productMapper.deleteProduct(productId);
        modelAndView.setViewName("redirect:/admin/adminProduct");
        return modelAndView;
    }

    @Override
    public ModelAndView addProduct(Product product) {
        ModelAndView modelAndView = new ModelAndView();
        productMapper.addProduct(product);
        modelAndView.setViewName("adminIndex");
        return modelAndView;
    }
}
