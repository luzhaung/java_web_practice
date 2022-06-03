/**
 * @author: luzhuang
 * @email: luzhuang_zou@163.com
 * @date: 2022/5/22
 * @time: 18:36
 */

package com.shiqidu;


import com.shiqidu.dao.impl.StudentDaoImpl;
import com.shiqidu.domain.Student;
import org.junit.Test;

import java.util.List;

public class AppTest2 {

    @Test
    public void testSelectById()
    {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        Student student = studentDao.selectById(1);
        System.out.println(student);
    }

    @Test
    public void testSelectAll()
    {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.selectAll();
        System.out.println(students);
    }
}
