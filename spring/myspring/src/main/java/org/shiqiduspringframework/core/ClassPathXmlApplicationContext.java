package org.shiqiduspringframework.core;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

            // 给对象赋值
            nodes.forEach(node -> {
                try {
                    Element beanElement = (Element) node;
                    String id = beanElement.attributeValue("id");
                    String className = beanElement.attributeValue("class");
                    Class<?> aClass = Class.forName(className);
                    beanElement.elements("property").forEach(property -> {
                        try {
                            // 获取属性名
                            String propertyName = property.attributeValue("name");
                            logger.info("propertyName = " + propertyName);
                            // 获取属性类型
                            Field field = aClass.getDeclaredField(propertyName);
                            // 获取set方法
                            String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                            Method setMethod = aClass.getDeclaredMethod(setMethodName, field.getType());
                            // 获取具体的值
                            String value = property.attributeValue("value");
                            Object actualValue = null;
                            String ref = property.attributeValue("ref");
                            if (value != null) {
                                // 只支持 byte short int long float double boolean char
                                String propertyTypeSimpleName = field.getType().getSimpleName();
                                switch (propertyTypeSimpleName) {
                                    case "byte":
                                        actualValue = Byte.parseByte(value);
                                        break;
                                    case "short":
                                        actualValue = Short.parseShort(value);
                                        break;
                                    case "int":
                                        actualValue = Integer.parseInt(value);
                                        break;
                                    case "long":
                                        actualValue = Long.parseLong(value);
                                        break;
                                    case "float":
                                        actualValue = Float.parseFloat(value);
                                        break;
                                    case "double":
                                        actualValue = Double.parseDouble(value);
                                        break;
                                    case "boolean":
                                        actualValue = Boolean.parseBoolean(value);
                                        break;
                                    case "char":
                                        actualValue = value.charAt(0);
                                        break;
                                    case "Byte":
                                        actualValue = Byte.valueOf(value);
                                        break;
                                    case "Short":
                                        actualValue = Short.valueOf(value);
                                        break;
                                    case "Integer":
                                        actualValue = Integer.valueOf(value);
                                        break;
                                    case "Long":
                                        actualValue = Long.valueOf(value);
                                        break;
                                    case "Float":
                                        actualValue = Float.valueOf(value);
                                        break;
                                    case "Double":
                                        actualValue = Double.valueOf(value);
                                        break;
                                    case "Boolean":
                                        actualValue = Boolean.valueOf(value);
                                        break;
                                    case "Character":
                                        actualValue = value.charAt(0);
                                        break;
                                    case "String":
                                        actualValue = value;
                                }
                                // 调用方法
                                setMethod.invoke(singletonObjects.get(id), actualValue);
                            }
                            if (ref != null) {
                                // 调用方法
                                setMethod.invoke(singletonObjects.get(id), singletonObjects.get(ref));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
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
