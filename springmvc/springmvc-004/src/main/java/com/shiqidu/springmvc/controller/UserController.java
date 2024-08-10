package com.shiqidu.springmvc.controller;

import com.shiqidu.springmvc.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */

@Controller
public class UserController {

    @RequestMapping("/")
    public String register() {
        return "register";
    }

    //@RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    @PostMapping("/user/reg_bak")
    public String doRegister(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String[] interest = request.getParameterValues("interest");
        String intro = request.getParameter("intro");

        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(Arrays.toString(interest));
        System.out.println(intro);
        return "ok";
    }

    @PostMapping("/user/reg_bak2")
    public String doRegister(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("sex") Integer sex,
            @RequestParam("interest") String[] interest,
            @RequestParam("intro") String intro,
            @RequestParam(value = "age", defaultValue = "20") Integer age
    ) {
        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(Arrays.toString(interest));
        System.out.println(intro);
        return "ok";
    }

    @PostMapping("/user/reg_bak3")
    public String doRegister(String username, String password, Integer sex, String[] interest, String intro) {
        System.out.println(username);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(Arrays.toString(interest));
        System.out.println(intro);
        return "ok";
    }

    @PostMapping("/user/reg")
    public String doRegister(User user, @RequestHeader(value = "Referer", required = false, defaultValue = "") String referer) {
        System.out.println(user);
        System.out.println(referer);
        return "ok";
    }
}

