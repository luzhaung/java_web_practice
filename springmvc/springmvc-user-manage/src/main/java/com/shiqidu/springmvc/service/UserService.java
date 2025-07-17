package com.shiqidu.springmvc.service;

import com.shiqidu.springmvc.bean.User;
import com.shiqidu.springmvc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Long add(User user) {
        userMapper.add(user);
        return user.getId();
    }

    @Transactional(readOnly = true)
    public java.util.List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Transactional
    public Integer update(User user) {
        return userMapper.update(user);
    }

    @Transactional
    public Integer deleteById(Long id) {
        return userMapper.deleteById(id);
    }
}
