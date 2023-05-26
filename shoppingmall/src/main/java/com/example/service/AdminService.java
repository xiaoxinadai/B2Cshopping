package com.example.service;

import com.example.model.admin.Admin;
import org.springframework.web.servlet.ModelAndView;

public interface AdminService {

    ModelAndView toLogin();

    ModelAndView checkAdminLogin(Admin admin);

    ModelAndView toAdminProduct();
}
