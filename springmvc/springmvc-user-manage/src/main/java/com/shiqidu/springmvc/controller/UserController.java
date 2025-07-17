package com.shiqidu.springmvc.controller;

import com.shiqidu.springmvc.bean.User;
import com.shiqidu.springmvc.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    private UserDao userDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> users = UserDao.selectAll();
        // 存储到request域
        model.addAttribute("users", users);
        // 跳转视图
        return "user_list";
    }

    @PostMapping(value = "/user")
    public String save(User user) {
        Integer count = UserDao.save(user);
        System.out.println("insert count:" + count);
        return "redirect:/user";
    }
}
