package com.example.service.impl;

import com.example.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public ModelAndView toProductDetailView(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail");
        return modelAndView;
    }
}
