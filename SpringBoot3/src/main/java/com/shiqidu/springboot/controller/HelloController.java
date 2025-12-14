package com.shiqidu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzhuang
 * @version 1.0
 * @className HelloController
 * @since 1.0
 */

@RestController
// @Controller
public class HelloController {

    @GetMapping("/hello")
    // @ResponseBody
    public String hello() {
        return "hello, spring boot3";
    }
}
