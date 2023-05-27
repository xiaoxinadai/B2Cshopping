package com.example.service;

import com.example.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public interface AdminProductService {

    ModelAndView deleteProduct(Integer productId);

    ResponseEntity<Map<String, Object>> addProduct(Product product);
}
