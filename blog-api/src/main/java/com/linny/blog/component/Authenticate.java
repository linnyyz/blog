//package com.linny.blog.component;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.linny.blog.exceptions.PasswordNotRightException;
//import com.linny.blog.exceptions.UpdateException;
//import com.linny.blog.exceptions.UserIsBeenBandException;
//import com.linny.blog.exceptions.UsernameNotFoundException;
//import com.linny.blog.mapper.UserMapper;
//import com.linny.blog.pojo.dao.User;
//import com.linny.blog.utils.MD5;
//import lombok.SneakyThrows;
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class Authenticate implements AuthenticationManager {
//
//    @Autowired
//    UserMapper userMapper;
//
//    @SneakyThrows
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String username = (String) authentication.getPrincipal();
//        String password = (String) authentication.getCredentials();
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("username", username);
//        User user = userMapper.selectOne(wrapper);
//
//        String md5Password = MD5.getMD5Password(password, user.getSalt());//将登录密码加密
//        // 将加密密码与数据库的密码对比
//        if(!md5Password.equals(user.getPassword())){
//            throw new PasswordNotRightException("密码错误");
//        }
//        // 查询用户是否被禁用
//        if (user.getStatus()==1){
//            throw new UserIsBeenBandException("用户已被禁用");
//        }
//
//        // 更新用户最后一次登录时间
//        QueryWrapper wrap = new QueryWrapper<>();
//        wrap.eq("username",user.getUsername());
//        User userLoginTime = new User();
//        userLoginTime.setLastLogin(new Date());
//        Integer row = userMapper.update(userLoginTime,wrap);
//        if (row != 1) {
//            throw new UpdateException("用户登录异常");
//        }
//
//        return authentication;
//    }
//}
