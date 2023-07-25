package com.shiqidu.bank.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SqlSessionUtil {
    private SqlSessionUtil() {
    }

    private static SqlSessionFactory sessionFactory;

    // 静态代码块，类加载时调用一次
    static {
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取会话对象
     * @return 会话对象
     */
    public static SqlSession openSession() {
        return sessionFactory.openSession();
    }
}
