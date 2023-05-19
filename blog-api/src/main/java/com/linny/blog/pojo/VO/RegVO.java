package com.linny.blog.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegVO implements Serializable {

    //用户名
    private String username;
    //用户密码
    private String password;
    //盐值
    private String salt;

}
