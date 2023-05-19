package com.linny.blog.utils;

import org.springframework.util.DigestUtils;

public class MD5 {
    /**
     * 定义一个MD5算法加密处理
     */
    public static String getMD5Password(String password, String salt) {
        //MD5加密算法的调用(进行三次加密)
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
