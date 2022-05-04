package com.shiqidu;

import static org.junit.Assert.assertTrue;

import com.shiqidu.domain.Student;
import com.shiqidu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testSelectById()
    {
        SqlSession session = MyBatisUtil.getSqlSession(true);
        Student student = session.selectOne("com.shiqidu.dao.StudentDao.selectById", 1);
        System.out.println(student);
        session.close();
    }

    @Test
    public void testSelectAll()
    {
        SqlSession session = MyBatisUtil.getSqlSession(true);
        List<Student> students = session.selectList("com.shiqidu.dao.StudentDao.selectAll");
        System.out.println(students);
        session.close();
    }
}
