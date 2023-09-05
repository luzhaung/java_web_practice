package com.shiqidu.spring6.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */
public class VipDao {
    private static final Logger logger = LoggerFactory.getLogger(VipDao.class);

    public void insert()
    {
        logger.info("数据库正在保存VIP用户信息");
    }
}
