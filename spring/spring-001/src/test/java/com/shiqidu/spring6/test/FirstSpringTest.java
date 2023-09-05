package com.shiqidu.spring6.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class FirstSpringTest {

    @Test
    public void testLog4j() {
        Logger logger = LoggerFactory.getLogger(FirstSpringTest.class);
        logger.info("我是一条消息");
        logger.debug("我是一条调试信息");
        logger.error("我是一条错误信息");
    }

    @Test
    public void testFirstSpringCode() {
        // spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userDaoBean = applicationContext.getBean("userDaoBean");
        System.out.println(userDaoBean);
    }

    @Test
    public void testHttpRequest() throws IOException, URISyntaxException {
        String urlString = "https://clearplate-server-buff.oss-cn-beijing.aliyuncs.com/api/systemConf.json";
        URI uri = new URI(urlString);
        URL urlObj = uri.toURL();
        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
        urlConnection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        urlConnection.disconnect();
        System.out.println(response);
    }
}
