package com.shiqidu.dao;

import com.shiqidu.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    Student selectById(Integer id);

    List<Student> selectAll();

    List<Student> selectByNameOrAge(@Param("myname") String name, @Param("myage") Integer age);

    List<Student> selectByStudent(Student student);
    List<Student> selectByPosition(String name, Integer age);
    List<Student> selectByMap(Map<String,Object> student);
}
