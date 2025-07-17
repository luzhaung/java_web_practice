package com.shiqidu.springmvc.mapper;

import com.shiqidu.springmvc.bean.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    Integer add(User user);
}
