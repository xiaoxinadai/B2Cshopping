package com.example.service.impl;

import com.example.dto.OrderDto;
import com.example.dto.ShoppingCartDto;
import com.example.mapper.OrderMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.model.Product;
import com.example.model.ShoppingCart;
import com.example.model.User;
import com.example.service.PersonalCenterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ModelAndView toMyOrderFormView(Integer pageNum,Integer pageSize,HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        PageHelper.startPage(pageNum,pageSize);
        //到数据库中查询信息
        User user = (User) httpSession.getAttribute("successLogin");
        List<OrderDto> allMessageList = orderMapper.findAllMessage(user.getId());
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
        PageInfo pageInfo = new PageInfo(allMessageList);
        modelAndView.addObject("allMessageList",allMessageList);
        modelAndView.addObject("pageTotal",pageInfo.getTotal());
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.setViewName("personalCenter");
        return modelAndView;
    }

    @Override
    public ResponseEntity<Map<String, Object>> editStatus(Integer orderId,String orderStatus) {
        orderMapper.editOrderStatus(orderId,orderStatus);
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        data.put("success",1);
        result.put("data",data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
