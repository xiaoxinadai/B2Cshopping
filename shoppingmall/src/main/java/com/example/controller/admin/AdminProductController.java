package com.example.controller.admin;

import com.example.model.Product;
import com.example.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminProduct")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @RequestMapping("/deleteProduct")
    public ModelAndView deleteProduct(@RequestParam Integer productId){
        return adminProductService.deleteProduct(productId);
    }

    @RequestMapping("/addProductView")
    public ModelAndView toAddProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminAddProduct");
        return modelAndView;
    }

    /**
     * 新增商品
     */
    @RequestMapping("/addProductHandle")
    public ModelAndView addProduct(Product product){
        return adminProductService.addProduct(product);
    }
}
