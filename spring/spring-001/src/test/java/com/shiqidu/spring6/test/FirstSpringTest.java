package com.shiqidu.spring6.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpringTest {

    @Test
    public void testFirstSpringCode()
    {
        // spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);
    }
}
