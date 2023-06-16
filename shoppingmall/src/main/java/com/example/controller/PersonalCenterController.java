package com.example.controller;

import com.example.model.Product;
import com.example.service.PersonalCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

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

    @RequestMapping("/editStatus")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> editStatus(Integer orderId,String orderStatus){
        return personalCenterService.editStatus(orderId,orderStatus);
    }
}
