package com.example.controller;

import com.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/detail")
    public ModelAndView toDetailView(@RequestParam Integer id){
        return productService.toProductDetailView(id);
    }

    @RequestMapping("/shoppingCart")
    public ModelAndView toShoppingCartView(
                                            @RequestParam(name = "productId",required = false) Integer productId,
                                            @RequestParam(name = "productSpec",required = false) String productSpec,
                                            @RequestParam(name = "amount",required = false) Integer amount,
                                            @RequestParam(name = "totalPrice",required = false) Double totalPrice) {
        return productService.savaDataToCart(productId,productSpec,amount,totalPrice);
    }
}
