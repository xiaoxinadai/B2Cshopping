package com.example.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class User {

    private Integer id;         // 主键id

    private String username;    // 用户名

    private String password;    //密码

    private String phone;       //手机号码

    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime createTime;   //账户创建时间

    private String headSculpture;       //头像

    private String captcha;             //验证码

    public String getCreateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        return createTime.format(formatter);
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
