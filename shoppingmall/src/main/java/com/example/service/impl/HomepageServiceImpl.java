package com.example.service.impl;

import com.example.service.HomepageService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class HomepageServiceImpl implements HomepageService {
    @Override
    public ModelAndView toHomepageView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("homePage");
        return modelAndView;
    }
}
