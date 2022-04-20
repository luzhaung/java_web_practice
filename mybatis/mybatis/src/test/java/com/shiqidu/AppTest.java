package com.shiqidu;

import static org.junit.Assert.assertTrue;

import com.shiqidu.entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testGetStudentById() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();

        // 查询单个student
        Student stu = sqlSession.selectOne("com.shiqidu.dao.StudentDao.selectStudentById", 2);
        System.out.println(stu);

        System.out.println("==============================================================");
        // 查询所有student
        List<Student> list = sqlSession.selectList("com.shiqidu.dao.StudentDao.getAll");
        list.forEach(System.out::println);
        sqlSession.close();
    }
}
