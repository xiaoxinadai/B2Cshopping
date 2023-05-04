package com.example.controller;

import com.example.dto.ShoppingCartDto;
import com.example.service.ShoppingCartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping("/shoppingCart")
    public ModelAndView toShoppingCartView(
            @ModelAttribute("shoppingCartDtoListString") String shoppingCartDtoListString,
            HttpServletRequest httpServletRequest) throws JsonProcessingException {
        List<ShoppingCartDto> shoppingCartList = new ArrayList<>();
        if (shoppingCartDtoListString !=null && !"".equals(shoppingCartDtoListString)){
            ObjectMapper objectMapper = new ObjectMapper();
            shoppingCartList = objectMapper.readValue(shoppingCartDtoListString, new TypeReference<List<ShoppingCartDto>>() {});
        }
        return shoppingCartService.toShoppingCartView(httpServletRequest,shoppingCartList);
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
            @RequestParam Double settlePrice,
            HttpSession httpSession){
        return shoppingCartService.atOnceSettle(httpSession,shoppingCartArray,settlePrice);
    }
}
