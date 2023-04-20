package com.example.service.impl;

import com.example.dto.ShoppingCartDto;
import com.example.mapper.ProductMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.model.Product;
import com.example.model.ShoppingCart;
import com.example.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartMapper shoppingCartMapper;
    private ProductMapper productMapper;

    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper, ProductMapper productMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.productMapper = productMapper;
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
}
