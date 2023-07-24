package com.shiqidu.xml.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4jTest {
    @Test
    public void testParseMyBatisConfigXML() throws Exception {
        // 创建SAXReader
        SAXReader reader = new SAXReader();
        // 获取输入流
        InputStream inputSteam = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        // 读取XML文件
        Document document = reader.read(inputSteam);
        // System.out.println(document);
        // 获取文档标签
        // Element rootElement = document.getRootElement();
        // String rootElementName = rootElement.getName();
        // System.out.println(rootElementName);

        String xpath = "/configuration/environments";
        Element environments = (Element) document.selectSingleNode(xpath);
        String defaultEnvironmentId = environments.attributeValue("default");
        // System.out.println(defaultEnvironmentId);

        xpath = "/configuration/environments/environment[@id='" + defaultEnvironmentId + "']";
        Element environment = (Element) document.selectSingleNode(xpath);
        // 获取子节点
        Element transactionManager = environment.element("transactionManager");
        String transactionType = transactionManager.attributeValue("type");
        System.out.println("事务管理器的类型: " + transactionType);
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println("数据源的类型: " + dataSourceType);
        // 获取dataSource的所有子节点
        List<Element> propertyElements = dataSource.elements();
        propertyElements.forEach(propertyElement -> {
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            System.out.println(name + " : " + value);
        });

        // 解析Mapper标签
        xpath = "//mapper";
        List<Node> mappers = document.selectNodes(xpath);
        mappers.forEach(mapper -> {
            Element mapperElement = (Element) mapper;
            String resource = mapperElement.attributeValue("resource");
            System.out.println("resource : " + resource);
        });
    }

    @Test
    public void testParseSqlMapperXML() throws Exception {
        // 创建SAXReader
        SAXReader reader = new SAXReader();
        // 获取输入流
        InputStream inputSteam = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        // 读取XML文件
        Document document = reader.read(inputSteam);
        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");
        System.out.println("namespace: " + namespace);

        List<Element> elements = mapper.elements();
        elements.forEach(element -> {
            String id = element.attributeValue("id");
            System.out.println("id: " + id);
            String resultType = element.attributeValue("resultType");
            System.out.println("resultType: " + resultType);
            String sql = element.getTextTrim();
            System.out.println("sql: " + sql);
            String newSql = sql.replaceAll("#\\{[\\w$]+}", "?");
            System.out.println("newSql: " + newSql);
        });
    }
}
