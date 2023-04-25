package com.example.service;

import com.example.model.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;

public interface UserService {

    ModelAndView checkLogin(User user, HttpSession httpSession, HttpServletRequest httpServletRequest);

    ModelAndView registerView();

    ModelAndView checkRegister(User user);

    ModelAndView toLogout(HttpSession httpSession);
}
