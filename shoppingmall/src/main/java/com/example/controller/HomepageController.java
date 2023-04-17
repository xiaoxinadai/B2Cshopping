package com.example.controller;

import com.example.service.HomepageService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@RequestMapping("/homepage")
public class HomepageController {

    private HomepageService homepageService;

    public HomepageController(HomepageService homepageService) {
        this.homepageService = homepageService;
    }

    @RequestMapping("/view")
    public ModelAndView toHomePageView() {
        return homepageService.toHomepageView();
    }
}
