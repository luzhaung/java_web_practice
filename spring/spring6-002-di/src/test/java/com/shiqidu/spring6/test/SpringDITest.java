package com.shiqidu.spring6.test;

import com.shiqidu.spring6.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luzhuang
 * @version 1.0
 * @className SpringDITest
 * @since 1.0
 */
public class SpringDITest {
    @Test
    public void testDI() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean("userServiceBean", UserService.class);
        userService.saveUser();
    }
}
