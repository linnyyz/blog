package com.linny.blog.pojo.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO implements Serializable {

    private String username;    // 用户名
    private String password;    // 用户密码
    private String code;        // 验证码

}
