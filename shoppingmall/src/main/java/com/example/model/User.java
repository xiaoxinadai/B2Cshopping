package com.example.model;

import lombok.Data;

@Data
public class User {

    private Integer id;         // 主键id

    private String username;    // 用户名

    private String password;    //密码

    private String phone;       //手机号码

    private String captcha;     //验证码
}
