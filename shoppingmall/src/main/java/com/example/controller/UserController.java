package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 跳转登录页面
     */
    @GetMapping("/login")
    public ModelAndView loginTest(
            @ModelAttribute("captchaError") String captchaError,
            @ModelAttribute("checkLoginError") String checkLoginError,
            @ModelAttribute("registerSign") String registerSign) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("captchaError", captchaError);
        modelAndView.addObject("checkLoginError", checkLoginError);
        modelAndView.addObject("registerSign", registerSign);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 登录信息验证
     */
    @PostMapping("/checkLogin")
    public ModelAndView checkLogin(User user, HttpSession httpSession, HttpServletRequest httpServletRequest) {
        return userService.checkLogin(user, httpSession, httpServletRequest);
    }

    /**
     * 跳转用户注册界面
     */
    @RequestMapping("/register")
    public ModelAndView registerView(@ModelAttribute("registerError") String registerError) {
        if (registerError != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("registerError", registerError);
            modelAndView.setViewName("register");
            return modelAndView;
        }
        return userService.registerView();
    }

    /**
     * 注册信息验证
     * 1、用户名不可重复
     */
    @RequestMapping("/checkRegister")
    public ModelAndView checkRegister(User user, HttpSession httpSession, HttpServletRequest httpServletRequest) {
        return userService.checkRegister(user);
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public ModelAndView toLogout(HttpSession httpSession){
        return userService.toLogout(httpSession);
    }



    /**
     * 验证码
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
