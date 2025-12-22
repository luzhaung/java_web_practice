package com.shiqidu.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiqidu.springboot.bean.User;
import com.shiqidu.springboot.result.R;
import com.shiqidu.springboot.service.UserService;
import com.shiqidu.springboot.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping("/list/{pageNo}")
    public R<PageInfo<User>> list(@PathVariable("pageNo") int pageNo) {
        // 1. 设置当前页码何每页显示多少条
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        // 2. 查询所有数据
        List<User> users = userService.findAll();
        // 3. 将数据封装到pageInfo对象中
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return R.success(userPageInfo);
    }
}
