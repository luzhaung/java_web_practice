package com.shiqidu.springmvc.dao;

import com.mysql.cj.log.Log;
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
        SqlSessionUtil.close(sqlSession);
        return users;
    }

    public Long add(User user) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("add user: " + user);
        userMapper.add(user);
        sqlSession.commit();
        Long id = user.getId();
        SqlSessionUtil.close(sqlSession);
        return id;
    }

    public User getById(Long id) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getById(id);
        System.out.println("get user: " + user);
        SqlSessionUtil.close(sqlSession);
        return user;
    }

    public Integer update(User user) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer affectRows = userMapper.update(user);
        System.out.println("affectRows: " + affectRows);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return affectRows;
    }

    public Integer deleteById(Long id) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer affectRows = userMapper.deleteById(id);
        System.out.println("deleteRows: " + affectRows);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
        return affectRows;
    }
}
