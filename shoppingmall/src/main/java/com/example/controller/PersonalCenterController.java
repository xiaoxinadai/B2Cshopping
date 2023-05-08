package com.example.controller;

import com.example.service.PersonalCenterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/personal")
public class PersonalCenterController {

    private PersonalCenterService personalCenterService;

    public PersonalCenterController(PersonalCenterService personalCenterService) {
        this.personalCenterService = personalCenterService;
    }

    @RequestMapping("/order/form")
    public ModelAndView toMyOrderFormView(
                                            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                            @RequestParam(required = false,defaultValue = "1") Integer pageSize,
                                            HttpSession httpSession){
        return personalCenterService.toMyOrderFormView(pageNum,pageSize,httpSession);
    }
}
