package com.shiqidu.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class CarMapperTest {

    @Test
    public void testInsertCar() {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

            // 获取sqlSessionFactory对象
            // Resources.getResourceAsStream默认从类的根目录下查找资源
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            // InputStream is = ClassLoader.getPlatformClassLoader().getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

            // 获取sqlSessionFactory对象
            sqlSession = sqlSessionFactory.openSession();

            // 执行SQL
            int count = sqlSession.insert("insertCar");
            sqlSession.commit();

            System.out.println("插入了几记录:" + count);
        } catch (Exception e) {
            e.printStackTrace();
            if (sqlSession != null) {
                sqlSession.close();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
