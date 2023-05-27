package com.example.controller.admin;

import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/adminProduct")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/deleteProduct")
    public ModelAndView deleteProduct(@RequestParam Integer productId){
        return adminProductService.deleteProduct(productId);
    }

    @RequestMapping("/addProductView")
    public ModelAndView toAddProduct(@RequestParam(required = false) Integer productId){
        ModelAndView modelAndView = new ModelAndView();
        if (productId != null){
            Product messageById = productMapper.findMessageById(productId);
            modelAndView.addObject("messageById",messageById);
            modelAndView.addObject("imgClose",1);
        }else {
            Product product = new Product();
            modelAndView.addObject("messageById",product);
            modelAndView.addObject("imgClose",0);
        }
        modelAndView.setViewName("adminAddProduct");
        return modelAndView;
    }

    /**
     * 新增商品
     */
    @RequestMapping("/addProductHandle")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addProduct(Product product){
        return adminProductService.addProduct(product);
    }
}
