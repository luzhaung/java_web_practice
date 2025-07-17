package com.shiqidu.springmvc.dao;

import com.shiqidu.springmvc.bean.User;
import com.shiqidu.springmvc.mapper.UserMapper;
import com.shiqidu.springmvc.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDao {
    public List<User> selectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println("users: " + users);
        sqlSession.close();
        return users;
    }

    public Long save(User user) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("add user: " + user);
        userMapper.add(user);
        sqlSession.commit();
        sqlSession.close();
        return user.getId();
    }
}
