package com.shiqidu.myspring.test;

import org.junit.Test;
import org.shiqiduspringframework.core.ApplicationContext;
import org.shiqiduspringframework.core.ClassPathXmlApplicationContext;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */
public class MySpringTest {

    @Test
    public void testMySpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myspring.xml");

    }
}
