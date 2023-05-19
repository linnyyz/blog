package com.linny.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.linny.blog.pojo.dao.User;

import java.util.Date;

@Mapper
public interface UserMapper extends BaseMapper<User> {

//    Integer updateLastLoginTimeByUsername(String username, Date time);

}
