package com.linny.blog.controller;


import com.linny.blog.exceptions.AllException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.linny.blog.pojo.VO.LoginVO;
import com.linny.blog.pojo.VO.RegVO;
import com.linny.blog.service.UserService;
import com.linny.blog.utils.JsonResult;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;




    @PostMapping("/reg")
    public JsonResult regUser(RegVO regVO) throws AllException {
        userService.userReg(regVO);
        return JsonResult.success(null);
    }


    @PostMapping("/login")
    public JsonResult login(LoginVO loginvo) throws AllException {
        String token = userService.login(loginvo);
        return JsonResult.success("token:"+token);
    }



}
