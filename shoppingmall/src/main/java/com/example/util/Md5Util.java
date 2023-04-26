package com.example.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * md5加密
 */
public class Md5Util {

    /**
     * 对字符串进行加密处理
     */
    public static String encrypt(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }
}
