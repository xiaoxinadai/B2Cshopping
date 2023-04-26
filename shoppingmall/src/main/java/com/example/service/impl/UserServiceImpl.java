package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.Md5Util;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ModelAndView checkLogin(User user, HttpSession httpSession, HttpServletRequest httpServletRequest) {

        ModelAndView modelAndView = new ModelAndView();

        String loginUsername = user.getUsername();
        String loginPassword = user.getPassword();
        String loginPasswordMd5 = Md5Util.encrypt(loginPassword);
        List<User> userList = userMapper.queryByLoginUsername(loginUsername);
        //验证码判断
        if (!CaptchaUtil.ver(user.getCaptcha(),httpServletRequest)){
            //清空session中的验证码
            CaptchaUtil.clear(httpServletRequest);
            modelAndView.addObject("captchaError","验证码有误!");
            //校验用户名和密码
            //根据用户名去查询数据库中的用户名和密码，然后进行密码的比较
            if (userList.size() == 0 || !loginPasswordMd5.equals(userList.get(0).getPassword())){
                modelAndView.addObject("checkLoginError","请检测用户名和密码;");
            }
            modelAndView.setViewName("redirect:/user/login");
            return modelAndView;
        }
        if (userList.size()>0 && loginPasswordMd5.equals(userList.get(0).getPassword())){
            modelAndView.setViewName("redirect:/homepage/view");
            httpSession.setAttribute("successLogin",user);
            return modelAndView;
        }else {
            modelAndView.addObject("checkLoginError","请检测用户名和密码;");
            modelAndView.setViewName("redirect:/user/login");
            return modelAndView;
        }
    }

    @Override
    public ModelAndView registerView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @Override
    public ModelAndView checkRegister(User user) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("-----------------------"+user.toString());
        //接收到注册信息，与数据库中的做对比看用户名是否重复
        if (user.getUsername().isEmpty()){
            modelAndView.addObject("registerError","输入信息有误");
            modelAndView.setViewName("redirect:/user/register");
            return modelAndView;
        }
        String registerUsername = user.getUsername();
        List<User> userList = userMapper.checkUsername(registerUsername);
        if (userList.size()>0){
            modelAndView.addObject("registerError","用户名已存在");
            modelAndView.setViewName("redirect:/user/register");
            return modelAndView;
        }
        //用户名没有重复，则保存到数据库中
        //md5加密
        String passwordMd5 = Md5Util.encrypt(user.getPassword());
        user.setPassword(passwordMd5);
        Integer saveRegisterUserNum = userMapper.saveRegisterUser(user);
        if(saveRegisterUserNum>0){
            modelAndView.addObject("registerSign","1");
            modelAndView.setViewName("redirect:/user/login");
            return modelAndView;
        }
        modelAndView.setViewName("404");
        return modelAndView;
    }

    @Override
    public ModelAndView toLogout(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        httpSession.removeAttribute("successLogin");
        modelAndView.setViewName("redirect:/user/login");
        return modelAndView;
    }
}
