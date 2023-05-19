package com.linny.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.linny.blog.exceptions.AllException;
import com.linny.blog.exceptions.UsernameNotFoundException;
import com.linny.blog.mapper.UserMapper;
import com.linny.blog.pojo.dao.User;
import com.linny.blog.service.IUserDetailsService;
import com.linny.blog.userDetails.LoginUser;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IUserDetailsServiceImpl implements IUserDetailsService {

    @Autowired
    UserMapper userMapper;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        LambdaQueryWrapper<com.linny.blog.pojo.dao.User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //封装成UserDetails对象返回，其中LoginUser为UserDetails的实现类
        List<String> list = new ArrayList<>();
        list.add("test");
        return new LoginUser(user,list);
    }
}
