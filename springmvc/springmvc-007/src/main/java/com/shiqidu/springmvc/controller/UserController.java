package com.shiqidu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        System.out.println("正在查询所有用户信息...");
        return "ok";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") String id) {
        System.out.println("正在根据用户地查询用户信息，用户Id是：" + id);
        return "ok";
    }
}
