/**
 * @author: luzhuang
 * @email: luzhuang_zou@163.com
 */

package com.shiqidu.dao.impl;

import com.shiqidu.dao.StudentDao;
import com.shiqidu.domain.Student;
import com.shiqidu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student selectById(Integer id) {
        SqlSession session = MyBatisUtil.getSqlSession(true);
        Student student = session.selectOne("com.shiqidu.dao.StudentDao.selectById", id);
        System.out.println(student);
        session.close();
        return student;
    }

    @Override
    public List<Student> selectAll() {
        SqlSession session = MyBatisUtil.getSqlSession(true);
        List<Student> students = session.selectList("com.shiqidu.dao.StudentDao.selectAll");
        System.out.println(students);
        session.close();
        return students;
    }

    @Override
    public List<Student> selectByNameOrAge(String name, Integer age) {
        return null;
    }

    @Override
    public List<Student> selectByStudent(Student student) {
        return null;
    }

    @Override
    public List<Student> selectByPosition(String name, Integer age) {
        return null;
    }

    @Override
    public List<Student> selectByMap(Map<String, Object> student) {
        return null;
    }
}
