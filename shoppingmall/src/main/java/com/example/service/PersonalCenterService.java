package com.example.service;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface PersonalCenterService {

    ModelAndView toMyOrderFormView(Integer pageNum,Integer pageSize,HttpSession httpSession);
}
