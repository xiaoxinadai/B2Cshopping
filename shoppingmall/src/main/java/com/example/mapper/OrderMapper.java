package com.example.mapper;

import com.example.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Transactional
    void saveOrderData(String str,Double settlePrice);

    List<OrderDto> findAllMessage();
}