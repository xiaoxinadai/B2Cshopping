package com.example.service.impl;

import com.example.dto.ShoppingCartDto;
import com.example.mapper.OrderMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.ShoppingCartService;
import com.example.util.OrderNumberUtil;
import com.example.util.OrderTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ModelAndView toShoppingCartView(HttpServletRequest httpServletRequest, List<ShoppingCartDto> shoppingCartList) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpServletRequest.getSession().getAttribute("shoppingCartSettleUnLogin")!=null && httpServletRequest.getSession().getAttribute("successLogin")!=null){
            List<ShoppingCartDto> resultList = new ArrayList<>();
            shoppingCartList = (List<ShoppingCartDto>) httpServletRequest.getSession().getAttribute("shoppingCartSettleUnLogin");
            User user = (User) httpServletRequest.getSession().getAttribute("successLogin");
            for (ShoppingCartDto shoppingCartDto:shoppingCartList){
                //将sessionStorage中的商品信息保存到数据库中
                shoppingCartMapper.insertShoppingCartDtoMessage(shoppingCartDto,user.getId());
            }
            //根据用户id查询购物车信息
            shoppingCartList = shoppingCartMapper.findCartMessageByUserId(user.getId());
            for (ShoppingCartDto shoppingCartDto:shoppingCartList){
                Integer productId = shoppingCartDto.getProductId();
                //根据商品id查询出商品的信息放到dto中
                Product productMessageById = productMapper.findMessageById(productId);
                shoppingCartDto.setProduct(productMessageById);
                resultList.add(shoppingCartDto);
            }
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.removeAttribute("shoppingCartSettleUnLogin");
            modelAndView.addObject("shoppingCartMessageList",resultList);
            modelAndView.setViewName("shoppingCart");
            return modelAndView;
        }
        if (shoppingCartList!=null && shoppingCartList.size()>0){
            List<ShoppingCartDto> resultList = new ArrayList<>();
            for (ShoppingCartDto shoppingCartDto:shoppingCartList){
                Integer productId = shoppingCartDto.getProductId();
                //根据商品id查询出商品的信息放到dto中
                Product productMessageById = productMapper.findMessageById(productId);
                shoppingCartDto.setProduct(productMessageById);
                resultList.add(shoppingCartDto);
            }
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.setAttribute("shoppingCartSettleUnLogin",shoppingCartList);
            modelAndView.addObject("shoppingCartMessageList",resultList);
        }else {
            List<ShoppingCartDto> resultList = new ArrayList<>();
            List<ShoppingCartDto> shoppingCartMessageList = shoppingCartMapper.findShoppingCartMessage();
            for (ShoppingCartDto shoppingCartDto:shoppingCartMessageList){
                Integer productId = shoppingCartDto.getProductId();
                //根据商品id查询出商品的信息放到dto中
                Product productMessageById = productMapper.findMessageById(productId);
                shoppingCartDto.setProduct(productMessageById);
                resultList.add(shoppingCartDto);
            }
            HttpSession httpSession = httpServletRequest.getSession();
            httpSession.removeAttribute("shoppingCartSettleUnLogin");
            modelAndView.addObject("shoppingCartMessageList",resultList);
        }
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
    public ModelAndView atOnceSettle(HttpSession httpSession,Integer[] shoppingCartArray,Double settlePrice) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("successLogin")!=null){
            String str = Arrays.stream(shoppingCartArray).map(Objects::toString).collect(Collectors.joining(","));
            User user = (User) httpSession.getAttribute("successLogin");
            String orderNumber = OrderNumberUtil.OrderNumber();
            String orderTime = OrderTimeUtil.orderTime();
            orderMapper.saveOrderData(str,settlePrice,user.getId(),orderNumber,orderTime);
        }
        modelAndView.setViewName("redirect:/personal/order/form");
        return modelAndView;
    }
}
