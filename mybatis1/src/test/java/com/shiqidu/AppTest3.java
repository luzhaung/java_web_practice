package com.shiqidu;

import com.shiqidu.dao.StudentDao;
import com.shiqidu.domain.Student;
import com.shiqidu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest3
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
        StudentDao mapper = session.getMapper(StudentDao.class);
        Student student = mapper.selectById(1);
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

    @Test
    public void testSelectByNameOrAge()
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectByNameOrAge("张三", 22);
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByStudent()
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("王五");
        student.setAge(34);
        List<Student> students = dao.selectByStudent(student);
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByPosition()
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.selectByPosition("王五", 33);
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByMap()
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        HashMap<String, Object> student = new HashMap<>();
        student.put("name", "王五");
        student.put("age", 33);
        List<Student> students = dao.selectByMap(student);
        students.forEach(System.out::println);
        sqlSession.close();
    }
}
