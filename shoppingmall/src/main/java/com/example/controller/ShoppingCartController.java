package com.example.controller;

import com.example.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView toShoppingCartView(){
        return shoppingCartService.toShoppingCartView();
    }

    //在购物车更改商品数量时，每隔一段时间保存一下数据
    @PostMapping("/updateDataFromCart")
    @ResponseBody
    public void updateDataFromCart(
                                    @RequestParam Integer productCartId,
                                    @RequestParam Integer productAmount,
                                    @RequestParam Double totalPrice){
        shoppingCartService.updateDataFromCart(productCartId,productAmount,totalPrice);
    }

    //点击删除按钮删除购物车商品
    @PostMapping("/deleteByCartId")
    @ResponseBody
    public void deleteByCartId(@RequestParam Integer productCartId){
        shoppingCartService.deleteByCartId(productCartId);
    }

    //在购物车页面点击立即结算按钮生成订单
    @PostMapping("/atOnceSettle")
    public ModelAndView atOnceSettle(
                                        @RequestParam Integer[] shoppingCartArray,
                                        @RequestParam Double settlePrice){
        return shoppingCartService.atOnceSettle(shoppingCartArray,settlePrice);
    }
}
