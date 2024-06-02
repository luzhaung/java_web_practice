package com.shiqidu.myspring.test;

import com.shiqidu.myspring.annotation.Component;
import com.shiqidu.myspring.bean.OrderService;
import org.junit.Test;
import org.shiqiduspringframework.core.ApplicationContext;
import org.shiqiduspringframework.core.ClassPathXmlApplicationContext;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        Object vip = applicationContext.getBean("vip");
        System.out.println(vip);

        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        orderService.generateOrder();
    }

    @Test
    public void testScanClass() {
        Map<String, Object> beanMap = new HashMap<>();
        String packageName = "com.shiqidu.myspring.bean";
        String packPath = packageName.replaceAll("\\.", "/");
        URL url = ClassLoader.getSystemClassLoader().getResource(packPath);
        String path = url.getPath();
        File file = new File(path);
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(f -> {
            String s = f.getName().split("\\.")[0];
            String className = packageName + "." + s;
            try {
                Class<?> aClass = Class.forName(className);
                if (aClass.isAnnotationPresent(Component.class)) {
                    Component annotation = aClass.getAnnotation(Component.class);
                    String id = annotation.value();
                    Object o = aClass.newInstance();
                    beanMap.put(id, o);
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(beanMap);
    }
}
