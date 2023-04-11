package com.example.service;

import com.example.model.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {

    ModelAndView checkLogin(User user, HttpSession httpSession, HttpServletRequest httpServletRequest);

    ModelAndView registerView();

    ModelAndView checkRegister(User user);
}
