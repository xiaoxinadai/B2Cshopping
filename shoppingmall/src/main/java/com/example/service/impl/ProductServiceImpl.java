package com.example.service.impl;

import com.example.dto.ShoppingCartDto;
import com.example.mapper.ProductMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;
    private ShoppingCartMapper shoppingCartMapper;

    public ProductServiceImpl(ProductMapper productMapper, ShoppingCartMapper shoppingCartMapper) {
        this.productMapper = productMapper;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Override
    public ModelAndView toProductDetailView(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        //根据id去查询相关信息
        Product productMessageById = productMapper.findMessageById(id);
        modelAndView.addObject("productMessageById",productMessageById);
        modelAndView.setViewName("detail");
        return modelAndView;
    }

    @Override
    public ModelAndView saveDataToCart(Integer productId, String productSpec, Integer amount, Double totalPrice, List<ShoppingCartDto> shoppingCartListArray, HttpSession httpSession, HttpServletResponse httpServletResponse) throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute("successLogin");
        if (user!=null){
            Integer userId = user.getId();
            //将信息保存到购物车
            shoppingCartMapper.savaMessageToCart(productId,productSpec,amount,totalPrice,userId);
        }
        if (shoppingCartListArray!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            String shoppingCartDtoListString = objectMapper.writeValueAsString(shoppingCartListArray);
            modelAndView.addObject("shoppingCartDtoListString",shoppingCartDtoListString);
            modelAndView.addObject("shopping","s");
        }
        modelAndView.setViewName("redirect:/shoppingCart/shoppingCart");
        return modelAndView;
    }
}
