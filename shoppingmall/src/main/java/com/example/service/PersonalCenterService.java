package com.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface PersonalCenterService {

    ModelAndView toMyOrderFormView(Integer pageNum,Integer pageSize,HttpSession httpSession);

    ResponseEntity<Map<String, Object>> editStatus(Integer orderId,String orderStatus);
}
