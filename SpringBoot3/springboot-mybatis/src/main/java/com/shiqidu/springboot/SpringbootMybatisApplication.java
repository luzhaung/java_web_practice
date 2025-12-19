package com.shiqidu.springboot;

import com.shiqidu.springboot.bean.User;
import com.shiqidu.springboot.dao.UserMapper;
import com.shiqidu.springboot.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

// 指定去哪里找Mapper接口
@MapperScan(basePackages = {"com.shiqidu.springboot.dao"})
@SpringBootApplication
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootMybatisApplication.class, args);
        // 获取service对象
        UserService userService = applicationContext.getBean(UserService.class);
        User user1 = new User("luzhuang", 20, 1);
        boolean save1 = userService.save(user1);
        System.out.println(save1);

        User user2 = new User("zousaisai", 20, 0);
        boolean save2 = userService.save(user2);
        System.out.println(save2);

        userService.findAll().forEach(System.out::println);

        applicationContext.close();
    }

}
