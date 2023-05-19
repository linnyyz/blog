package com.linny.blog.controller;


import com.linny.blog.component.WebSocketServer;
import com.linny.blog.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ws")
public class WebSocketController {

    //页面请求
    @GetMapping("/socket/{cid}")
    public JsonResult socket(@PathVariable String cid){
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid",cid);
        return JsonResult.success(mav);
    }

    @RequestMapping("/socket/push/{cid}")
    public JsonResult socketPush(@PathVariable String cid,String message) {
        WebSocketServer.sendInfo(message,cid);
        return JsonResult.success(null);
    }

}
