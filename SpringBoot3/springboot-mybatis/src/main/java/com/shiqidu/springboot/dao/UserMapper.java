package com.shiqidu.springboot.dao;

import com.shiqidu.springboot.bean.User;

import java.util.List;

/**
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */
public interface UserMapper {

    /**
     * 保存会员信息
     * @param user 会员信息
     * @return 1表示保存成功
     */
    int insert(User user);

    /**
     * 查询所有会员信息
     * @return 会员列表
     */
    List<User> selectAll();
}
