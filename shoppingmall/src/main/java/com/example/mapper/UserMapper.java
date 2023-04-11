package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    //保存注册用户信息
    Integer saveRegisterUser(User user);

    List<User> checkUsername(String registerUsername);
}
