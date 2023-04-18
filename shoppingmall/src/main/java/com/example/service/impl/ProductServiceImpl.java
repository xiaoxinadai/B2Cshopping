package com.example.service.impl;

import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
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
}
