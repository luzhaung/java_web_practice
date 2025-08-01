package com.shiqidu.springmvc.controller;

import com.shiqidu.springmvc.bean.User;
import com.shiqidu.springmvc.dao.UserDao;
import com.shiqidu.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    // 自动注入 UserService，你不用自己 new。
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> users = userService.selectAll();
        // 存储到request域
        model.addAttribute("users", users);
        // 跳转视图
        return "user_list";
    }

    @PostMapping(value = "/user")
    public String save(User user) {
        Long userId = userService.add(user);
        if (userId == 0) {
            throw new RuntimeException("新增失败");
        }
        return "redirect:/user";
    }

    @GetMapping("/user/{id}")
    public String toUpdate(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @PutMapping("/user")
    public String modify(User user) {
        Integer affectRows = userService.update(user);
        if (affectRows == 0) {
            throw new RuntimeException("更新失败");
        }
        return "redirect:/user";
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Long id) {
        Integer deleteRows = userService.deleteById(id);
        if (deleteRows == 0) {
            throw new RuntimeException("删除失败");
        }
        return "redirect:/user";
    }
}
