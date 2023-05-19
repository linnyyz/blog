package com.linny.blog.pojo.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDao implements Serializable {

    @TableField("created_time")
    private Date createTime;

    @TableField("updated_time")
    private Date editTime;

}
