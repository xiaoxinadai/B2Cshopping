package com.example.controller;

import com.example.dto.ShoppingCartDto;
import com.example.model.ShoppingCart;
import com.example.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/addToShoppingCart")
    public ModelAndView toShoppingCartView(
            @RequestParam(name = "productId",required = false) Integer productId,
            @RequestParam(name = "productSpec",required = false) String productSpec,
            @RequestParam(name = "amount",required = false) Integer amount,
            @RequestParam(name = "totalPrice",required = false) Double totalPrice,
            @RequestParam(name = "shoppingCartListArray",required = false) String shoppingCartListArraya,
            HttpSession httpSession,
            HttpServletResponse httpServletResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ShoppingCartDto> shoppingCartListArray = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(shoppingCartListArraya);
            for (JsonNode node : jsonNode) {
                ShoppingCartDto shoppingCartDto = objectMapper.convertValue(node, ShoppingCartDto.class);
                shoppingCartListArray.add(shoppingCartDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productService.saveDataToCart(productId,productSpec,amount,totalPrice,shoppingCartListArray,httpSession,httpServletResponse);
    }
}
