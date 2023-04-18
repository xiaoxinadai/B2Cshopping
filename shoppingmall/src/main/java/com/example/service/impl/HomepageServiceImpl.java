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
        // 查询所有数据从数据库中根据类别选择
        List<Product> allProductList = productMapper.findAllProduct();
        modelAndView.addObject("allProductList",allProductList);
        modelAndView.setViewName("homePage");
        return modelAndView;
    }
}
