package com.shiqidu.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory factory = null;

    static {
        String config = "mybatis.xml";
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(config);
            factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 获取sqlSession
    public static SqlSession getSqlSession(boolean autoCommit) {
        SqlSession session = null;
        if (factory != null) {
            session = factory.openSession(autoCommit);
        }
        return session;
    }
}
