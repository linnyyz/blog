package com.linny.blog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.linny.blog.pojo.VO.LoginVO;
import com.linny.blog.pojo.VO.RegVO;
import com.linny.blog.exceptions.AllException;
import com.linny.blog.pojo.dao.User;

public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param regVO 用户注册vo数据
     * @throws AllException
     */
    void userReg(RegVO regVO) throws AllException;

    String login(LoginVO loginvo) throws AllException;
}
