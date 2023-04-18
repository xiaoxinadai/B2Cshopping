package com.example.mapper;

import com.example.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAllProduct();

    Product findMessageById(Integer id);
}
