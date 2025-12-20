package com.shiqidu.springboot.controller;

import com.shiqidu.springboot.bean.User;
import com.shiqidu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/detail")
    public User detail(@RequestParam("id") Integer id) {
        return userService.findById(id);
    }
}
