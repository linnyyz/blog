package com.linny.blog.utils;

import java.util.Date;
import java.util.UUID;

public class Token {
    public static String getToken(String id) {
        String t = UUID.randomUUID().toString();
        String token = id+ "-"+new Date().getTime()+"-"+t;
        return token;
    }

    public static String getIdByToken(String token){
        String[] split = token.split("-");
        String id = split[0];
        return id;
    }
}
