package com.example.controller.admin;

import com.example.model.admin.Admin;
import com.example.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理员登录页面
     */
    @RequestMapping("/login")
    public ModelAndView toAdminLogin(){
        return adminService.toLogin();
    }

    /**
     * 用户名密码验证
     */
    @RequestMapping("/checkLogin")
    public ModelAndView checkAdminLogin(Admin admin){
        return adminService.checkAdminLogin(admin);
    }

    @RequestMapping("/adminProduct")
    public ModelAndView toAdminProduct(){
        return adminService.toAdminProduct();
    }
}
