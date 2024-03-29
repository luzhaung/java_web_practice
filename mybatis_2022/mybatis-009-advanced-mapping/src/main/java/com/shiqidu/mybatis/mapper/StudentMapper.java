package com.shiqidu.mybatis.mapper;

import com.shiqidu.mybatis.pojo.Student;

import java.util.List;

public interface StudentMapper {
    Student selectById(Integer id);

    Student selectByIdAssociation(Integer id);

    Student selectByIdStep1(Integer id);

    List<Student> selectByCidStep2(Integer cid);
}
