package com.shiqiduuser.myspring.bean;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */
public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        userDao.insert();
    }
}
