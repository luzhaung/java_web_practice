package com.shiqidu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */

@Controller
public class FirstController {

    @RequestMapping("/test")
    public String hehe() {
        // 返回的是逻辑试图名称
        return "first";
    }
}
