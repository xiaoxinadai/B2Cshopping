package com.example.service;

import org.springframework.web.servlet.ModelAndView;

public interface ProductService {

    ModelAndView toProductDetailView(Integer id);
}