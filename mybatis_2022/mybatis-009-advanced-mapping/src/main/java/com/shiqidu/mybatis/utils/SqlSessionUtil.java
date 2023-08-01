package com.shiqidu.mybatis.utils;

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

    // 全局的，服务器级别的，一个服务器定义一个
    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /**
     * 获取会话对象
     *
     * @return 会话对象
     */
    public static SqlSession openSession() {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = sessionFactory.openSession();
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭sqlSession
     * @param sqlSession 会话对象
     */
    public static void close(SqlSession sqlSession)
    {
        if (sqlSession != null) {
            sqlSession.close();
            local.remove();
        }
    }
}
