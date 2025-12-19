package com.shiqidu.springboot3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */
@Controller
@RestController
public class MyController {

    @RequestMapping("/hello")
    public String index() {
        return "hello world";
    }
}
