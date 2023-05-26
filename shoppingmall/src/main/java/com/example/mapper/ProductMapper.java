package com.example.mapper;

import com.example.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAllProduct();

    Product findMessageById(Integer id);

    List<Product> findManProduct();

    List<Product> findWomenProduct();

    List<Product> findOldManProduct();

    List<Product> findChildProduct();

    int deleteProduct(Integer productId);

    int addProduct(Product product);
}
