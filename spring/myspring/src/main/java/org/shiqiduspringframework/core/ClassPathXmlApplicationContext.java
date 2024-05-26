package org.shiqiduspringframework.core;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {

    private static final Logger logger = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);

    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 解析spring的配置文件，初始化所有的bean对象
     *
     * @param configLocation spring配置文件的路径，配置文件应该放在类路径下
     */
    public ClassPathXmlApplicationContext(String configLocation) {

        try {
            SAXReader reader = new SAXReader();
            InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);
            Document document = reader.read(in);
            List<Node> nodes = document.selectNodes("//bean");
            nodes.forEach(node -> {
                try {
                    Element beanElement = (Element) node;
                    String id = beanElement.attributeValue("id");
                    String className = beanElement.attributeValue("class");

                    logger.info("beanName = " + id);
                    logger.info("beanClassName = " + className);

                    Class<?> aClass = Class.forName(className);
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                    Object bean = declaredConstructor.newInstance();

                    singletonObjects.put(id, bean);
                    logger.info(singletonObjects.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String name) {
        return singletonObjects.get(name);
    }
}
