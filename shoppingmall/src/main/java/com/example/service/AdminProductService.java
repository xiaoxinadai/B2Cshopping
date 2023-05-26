package com.example.service;

import com.example.model.Product;
import org.springframework.web.servlet.ModelAndView;

public interface AdminProductService {

    ModelAndView deleteProduct(Integer productId);

    ModelAndView addProduct(Product product);
}
