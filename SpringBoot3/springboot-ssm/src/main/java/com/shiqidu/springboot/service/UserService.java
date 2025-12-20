package com.shiqidu.springboot.service;

import com.shiqidu.springboot.bean.User;

/**
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */
public interface UserService {
    User findById(Integer id);
}
