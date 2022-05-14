package com.shiqidu.dao;

import com.shiqidu.entity.Student;

public interface StudentDao {
    Student selectStudentById(Integer id);

    int insertStudent(Student student);
}
