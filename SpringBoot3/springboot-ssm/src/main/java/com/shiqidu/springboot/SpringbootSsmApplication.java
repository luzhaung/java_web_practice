package com.shiqidu.springboot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@MapperScan(basePackages = {"com.shiqidu.springboot.dao"})
@SpringBootApplication
public class SpringbootSsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSsmApplication.class, args);

        log.trace("trace级别日志");
        log.debug("debuge级别日志");
        log.info("info级别日志");
        log.warn("warn级别日志");
        log.error("error级别日志");
    }

}
