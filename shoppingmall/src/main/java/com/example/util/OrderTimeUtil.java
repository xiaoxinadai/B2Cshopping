package com.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTimeUtil {

    public static String orderTime(){

        LocalDateTime localDateTimeNow = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String format = dateTimeFormatter.format(localDateTimeNow);

        return format;
    }
}
