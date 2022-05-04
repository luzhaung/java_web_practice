package com.shiqidu.dao;

import com.shiqidu.domain.Student;

import java.util.List;

public interface StudentDao {

    Student selectById(Integer id);

    List<Student> selectAll();
}
