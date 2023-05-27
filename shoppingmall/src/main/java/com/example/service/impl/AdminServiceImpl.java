package com.example.service.impl;

import com.example.mapper.AdminMapper;
import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.model.admin.Admin;
import com.example.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    private ProductMapper productMapper;

    public AdminServiceImpl(AdminMapper adminMapper, ProductMapper productMapper) {
        this.adminMapper = adminMapper;
        this.productMapper = productMapper;
    }

    @Override
    public ModelAndView toLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @Override
    public ModelAndView checkAdminLogin(Admin admin) {
        ModelAndView modelAndView = new ModelAndView();
        String username = admin.getUsername();
        String password = admin.getPassword();
        Admin admin1 = adminMapper.queryAllMessage(username);
        String password1 = admin1.getPassword();
        if (password1!=null && password1.equals(password)){
            //登录成功
            modelAndView.setViewName("redirect:/admin/adminIndex");
        }else {
            modelAndView.addObject("error",1);
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @Override
    public ModelAndView toAdminProduct() {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> allProductList = productMapper.findAllProduct();
        modelAndView.addObject("allProductList",allProductList);
        modelAndView.setViewName("adminProduct");
        return modelAndView;
    }
}
