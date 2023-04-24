package com.example.service.impl;

import com.example.dto.OrderDto;
import com.example.dto.ShoppingCartDto;
import com.example.mapper.OrderMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.model.Product;
import com.example.model.ShoppingCart;
import com.example.service.PersonalCenterService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {

    private OrderMapper orderMapper;
    private ShoppingCartMapper shoppingCartMapper;
    private ProductMapper productMapper;

    public PersonalCenterServiceImpl(OrderMapper orderMapper, ShoppingCartMapper shoppingCartMapper, ProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.shoppingCartMapper = shoppingCartMapper;
        this.productMapper = productMapper;
    }

    @Override
    public ModelAndView toMyOrderFormView() {
        ModelAndView modelAndView = new ModelAndView();
        //到数据库中查询信息
        List<OrderDto> allMessageList = orderMapper.findAllMessage();
        for (int i = 0;i<allMessageList.size();i++){
            List<ShoppingCartDto> shoppingCartDtoList = new ArrayList<>();
            OrderDto orderDto = allMessageList.get(i);
            String[] split = orderDto.getProductCartId().split(",");
            for (int j = 0;j<split.length;j++){
                Integer productCartId = Integer.valueOf(split[j]);
                //根据购物车id查找商品id及其它所有信息，之后再根据商品id查找需要的信息
//                Integer productId = shoppingCartMapper.findProductIdByCartId(productCartId);
                ShoppingCart allMessageByCartId = shoppingCartMapper.findAllMessageByCartId(productCartId);
                Product product = productMapper.findMessageById(allMessageByCartId.getProductId());
                ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
                shoppingCartDto.setProduct(product);
                shoppingCartDto.setProductAmount(allMessageByCartId.getProductAmount());
                shoppingCartDtoList.add(shoppingCartDto);
            }
            orderDto.setShoppingCartDtoList(shoppingCartDtoList);
        }
        modelAndView.addObject("allMessageList",allMessageList);
        modelAndView.setViewName("personalCenter");
        return modelAndView;
    }
}
