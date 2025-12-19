package com.shiqidu.springboot.service.impl;

import com.shiqidu.springboot.bean.User;
import com.shiqidu.springboot.dao.UserMapper;
import com.shiqidu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean save(User user) {
        return userMapper.insert(user)>0;
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
