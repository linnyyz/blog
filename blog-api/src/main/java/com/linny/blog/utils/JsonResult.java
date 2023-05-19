package com.linny.blog.utils;

import lombok.Data;

@Data
public class JsonResult {

    private String msg="成功";
    private Integer code=200;
    private  Object data;

    public JsonResult(String msg,Integer code){
        this.msg=msg;
        this.code=code;
    }

    public JsonResult(Object data){
        this.data=data;
    }



    public static JsonResult fail(Integer code,String msg){
        return new JsonResult(msg,code);
    }

    public static JsonResult success(Object data){
        return new JsonResult(data);
    }
}
