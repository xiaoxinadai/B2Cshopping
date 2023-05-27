package com.example.service.impl;

import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> addProduct(Product product) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        if (product.getId()!=null){
            productMapper.updateProduct(product);
        }else {
            productMapper.addProduct(product);
        }
        data.put("closePopups",1);
        result.put("data",data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
