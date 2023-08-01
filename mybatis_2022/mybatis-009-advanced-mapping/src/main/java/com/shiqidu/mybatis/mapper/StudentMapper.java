package com.shiqidu.mybatis.mapper;

import com.shiqidu.mybatis.pojo.Student;

public interface StudentMapper {
    Student selectById(Integer id);

    Student selectByIdAssociation(Integer id);

    Student selectByIdStep1(Integer id);

}
