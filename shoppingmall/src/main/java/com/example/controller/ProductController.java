package com.example.controller;

import com.example.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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

    /* 测试前端通过js给后端传值 */
    @PostMapping("/test")
    public void test(@RequestParam Integer amount,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/text; charset=utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        System.out.println("-----------------------------------------"+amount);

        //返回Json键值对 (使用HashMap)
        Map<String, Object> ret = new HashMap<>();
        //可以在这里使用JDBC连接数据库，获得数据
        if (true) {
            ret.put("error", 0);
        }
        else {
            ret.put("error", 1);
            ret.put("errorInfo", "处理出错");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String testjson = objectMapper.writeValueAsString(ret);
        printWriter.write(testjson);
    }
}
