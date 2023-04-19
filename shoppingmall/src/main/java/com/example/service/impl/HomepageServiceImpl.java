package com.example.service.impl;

import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.service.HomepageService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class HomepageServiceImpl implements HomepageService {

    private ProductMapper productMapper;

    public HomepageServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public ModelAndView toHomepageView() {
        ModelAndView modelAndView = new ModelAndView();
        /**
         * 查询所有数据从数据库中根据类别选择
         */
        //所有
        List<Product> allProductList = productMapper.findAllProduct();
        //男士
        List<Product> manProductList = productMapper.findManProduct();
        //女士
        List<Product> womanProductList = productMapper.findWomenProduct();
        //老人
        List<Product> oldManProductList = productMapper.findOldManProduct();
        //儿童
        List<Product> childProductList = productMapper.findChildProduct();
        modelAndView.addObject("allProductList",allProductList);
        modelAndView.addObject("manProductList",manProductList);
        modelAndView.addObject("womanProductList",womanProductList);
        modelAndView.addObject("oldManProductList",oldManProductList);
        modelAndView.addObject("childProductList",childProductList);
        modelAndView.setViewName("homePage");
        return modelAndView;
    }
}
