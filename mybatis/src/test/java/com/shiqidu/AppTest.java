package com.shiqidu;

import static org.junit.Assert.assertTrue;

import com.mysql.cj.Session;
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

    @Test
    public void testAddStudent() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession(true);

        // 无参
        /*int affectRows = sqlSession.insert("com.shiqidu.dao.StudentDao.insertStudent");
        System.out.println("受影响行数: " + affectRows);*/

        // 有参数
        Student student = new Student();
        student.setId(6);
        student.setName("卢八");
        student.setAge(32);
        student.setEmail("luba@qq.com");
        int affectRowsWithParams = sqlSession.insert("com.shiqidu.dao.StudentDao.insertStudentWithStudent", student);
        System.out.println("受影响行数: " + affectRowsWithParams);
        // 关闭session链接
        sqlSession.close();
    }
}
