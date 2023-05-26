package com.example.mapper;

import com.example.model.admin.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    Admin queryAllMessage(String username);
}
