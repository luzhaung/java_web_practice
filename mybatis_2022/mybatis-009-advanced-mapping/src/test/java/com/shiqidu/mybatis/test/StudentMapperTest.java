package com.shiqidu.mybatis.test;

import com.shiqidu.mybatis.mapper.StudentMapper;
import com.shiqidu.mybatis.pojo.Student;
import com.shiqidu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdAssociation() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdAssociation(2);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testStudentResultMapByStep()
    {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdStep1(3);
        System.out.println(student);
        sqlSession.close();
    }
}
