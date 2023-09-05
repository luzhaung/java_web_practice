package com.shiqidu.spring6.service;

import com.shiqidu.spring6.dao.UserDao;
import com.shiqidu.spring6.dao.VipDao;

public class UserService {
    private UserDao userDao;
    private VipDao vipDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    /*public void setMySQLUserDao(UserDao xyz) {
        this.userDao = xyz;
    }*/

    public void setVipDao(VipDao vipDao) {
        this.vipDao = vipDao;
    }

    public void saveUser() {
        userDao.insert();
        vipDao.insert();
    }
}
