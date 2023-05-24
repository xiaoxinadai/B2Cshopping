package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 订单号工具类
 */
public class OrderNumberUtil {

    public static String OrderNumber(){

        LocalDateTime localDateTimeNow = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String format = dateTimeFormatter.format(localDateTimeNow);

        Random rand = new Random();
        int randomNum = rand.nextInt(900) + 100; // 生成100到999之间的随机整数
        String s = String.valueOf(randomNum);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(format).append(s);
        return stringBuilder.toString();
    }
}
