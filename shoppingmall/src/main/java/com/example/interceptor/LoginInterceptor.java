package com.example.interceptor;

import com.example.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        User loginUser = (User) httpSession.getAttribute("successLogin");
        if (loginUser!=null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/user/login");
            return false;
        }
    }
}
