package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/personal")
public class PersonalCenterController {

    @GetMapping("/order/form")
    public ModelAndView toMyOrderFormView(){
        ModelAndView modelAndView = new ModelAndView("personalCenter");
        return modelAndView;
    }
}
