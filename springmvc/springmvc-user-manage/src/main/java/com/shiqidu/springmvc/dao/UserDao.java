package com.shiqidu.springmvc.dao;

import com.shiqidu.springmvc.bean.User;
import com.shiqidu.springmvc.mapper.UserMapper;
import com.shiqidu.springmvc.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static SqlSession sqlSession = SqlSessionUtil.openSession();
    private static List<User> users = new ArrayList<User>();

    public static List<User> selectAll() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println("users: " + users);
        return users;
    }

    public static Integer save(User user) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("add user: " + user);
        return userMapper.add(user);
    }
}
