package com.example.service.impl;

import com.example.dto.ShoppingCartDto;
import com.example.mapper.OrderMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.model.Product;
import com.example.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartMapper shoppingCartMapper;
    private ProductMapper productMapper;
    private OrderMapper orderMapper;

    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper, ProductMapper productMapper, OrderMapper orderMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.productMapper = productMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public ModelAndView toShoppingCartView() {
        ModelAndView modelAndView = new ModelAndView();
        List<ShoppingCartDto> resultList = new ArrayList<>();
        List<ShoppingCartDto> shoppingCartMessageList = shoppingCartMapper.findShoppingCartMessage();
        for (ShoppingCartDto shoppingCartDto:shoppingCartMessageList){
            Integer productId = shoppingCartDto.getProductId();
            //根据商品id查询出商品的信息放到dto中
            Product productMessageById = productMapper.findMessageById(productId);
            shoppingCartDto.setProduct(productMessageById);
            resultList.add(shoppingCartDto);
        }
        modelAndView.addObject("shoppingCartMessageList",resultList);
        modelAndView.setViewName("shoppingCart");
        return modelAndView;
    }

    @Override
    public void updateDataFromCart(Integer productCartId, Integer productAmount, Double totalPrice) {
        shoppingCartMapper.updateDataFromCart(productCartId,productAmount,totalPrice);
    }

    @Override
    public void deleteByCartId(Integer productCartId) {
        shoppingCartMapper.deleteByCartId(productCartId);
    }

    @Override
    public ModelAndView atOnceSettle(Integer[] shoppingCartArray,Double settlePrice) {
        ModelAndView modelAndView = new ModelAndView();
        String str = Arrays.stream(shoppingCartArray).map(Objects::toString).collect(Collectors.joining(","));
        orderMapper.saveOrderData(str,settlePrice);
        modelAndView.setViewName("redirect:/personal/order/form");
        return modelAndView;
    }
}
