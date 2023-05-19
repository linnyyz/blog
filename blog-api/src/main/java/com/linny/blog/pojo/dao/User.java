package com.linny.blog.pojo.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseDao {

    private Integer id;     //用户id
    private String username;//用户名
    private String password;//密码
    private String salt;    //盐值


    private Integer status; // 是否被禁用 0未禁用 1已禁用

    @TableField("last_login")
    private Date lastLogin; //最后一次登录
}
