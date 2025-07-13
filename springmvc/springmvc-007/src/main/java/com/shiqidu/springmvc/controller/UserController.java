package com.shiqidu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAll() {
        System.out.println("正在查询所有用户信息...") ;
        return "ok";
    }
}
