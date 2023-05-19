package com.linny.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.linny.blog.component.Authenticate;
import com.linny.blog.exceptions.*;
import com.linny.blog.mapper.UserMapper;
import com.linny.blog.service.UserService;
import com.linny.blog.userDetails.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.linny.blog.pojo.VO.LoginVO;
import com.linny.blog.pojo.VO.RegVO;
import com.linny.blog.pojo.dao.User;
import com.linny.blog.utils.MD5;
import com.linny.blog.utils.Token;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthenticationManager authenticate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void userReg(RegVO regVO) throws AllException {

        String username = regVO.getUsername();
        String password = regVO.getPassword();

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);

        User user = userMapper.selectOne(wrapper);
        //查询用户名是否已存在
        if (!ObjectUtils.isEmpty(user)) {
            throw new UsernameBeenExistException("用户已存在");
        }
        //检查密码格式
        if (password.length() < 8 || password.length() > 16) {
            throw new PasswordFormatException("密码过短或过长");
        }

        String salt = UUID.randomUUID().toString();
//        String md5Password = MD5.getMD5Password(password, salt);
        String encode = passwordEncoder.encode(password);
        User realUser = new User();
//        realUser.setPassword(md5Password);
        realUser.setPassword(encode);
        realUser.setUsername(username);
        realUser.setSalt(salt);
        realUser.setStatus(0);
        realUser.setCreateTime(new Date());
        realUser.setEditTime(new Date());
        realUser.setLastLogin(new Date());

        int row = userMapper.insert(realUser);
        if (!(row == 1)) {
            throw new InsertException("用户注册异常");
        }
    }

    @Override
    public String login(LoginVO loginvo) throws AllException {
//        String username = loginvo.getUsername();
//        String password = loginvo.getPassword();
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("username", username);
//        User user = userMapper.selectOne(wrapper);
//        // 查询用户名是否存在
//        if (ObjectUtils.isEmpty(user)) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
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
//
//        redisTemplate.opsForValue().set("user",user,30, TimeUnit.SECONDS);
//
//        System.out.println(redisTemplate.opsForValue().get("user"));
//        //生成token
//        return Token.getToken(username);


        //在没认证之前principal，credentials两个参数分别存放用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginvo.getUsername(),
                        loginvo.getPassword()
                );

        //通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authentication = authenticate.authenticate(authenticationToken);

        //在认证信息authenticate中获取登录成功后的用户信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();
        // 查询用户是否被禁用
        if (user.getStatus() == 1) {
            throw new UserIsBeenBandException("用户已被禁用");
        }
        // 更新用户最后一次登录时间
        QueryWrapper wrap = new QueryWrapper<>();
        wrap.eq("username", user.getUsername());
        User userLoginTime = new User();
        userLoginTime.setLastLogin(new Date());
        Integer row = userMapper.update(userLoginTime, wrap);
        if (row != 1) {
            throw new UpdateException("用户登录异常");
        }

        //使用userid生成token
        String userId = loginUser.getUser().getId().toString();
        String token = Token.getToken(userId);
        //将用户id存入redis
        redisTemplate.opsForValue().set("userid", userId, 30, TimeUnit.SECONDS);
        return token;
    }
}
