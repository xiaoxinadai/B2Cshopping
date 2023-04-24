package com.example.controller;

import com.example.service.PersonalCenterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/personal")
public class PersonalCenterController {

    private PersonalCenterService personalCenterService;

    public PersonalCenterController(PersonalCenterService personalCenterService) {
        this.personalCenterService = personalCenterService;
    }

    @GetMapping("/order/form")
    public ModelAndView toMyOrderFormView(){
        return personalCenterService.toMyOrderFormView();
    }
}