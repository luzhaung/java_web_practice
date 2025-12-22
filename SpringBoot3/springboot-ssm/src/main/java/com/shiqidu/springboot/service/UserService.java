package com.shiqidu.springboot.service;

import com.shiqidu.springboot.bean.User;

import java.util.List;

/**
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */
public interface UserService {
    User findById(Integer id);

    List<User> findAll();
}
