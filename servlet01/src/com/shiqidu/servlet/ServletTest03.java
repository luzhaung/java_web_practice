package com.shiqidu.servlet;

import jakarta.servlet.*;
import jakarta.servlet.GenericServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ServletTest03 extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        ServletContext application = getServletContext();
        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            String value = application.getInitParameter(name);
            writer.print(name + "=" + value + "<br>");
        }

        // 获取项目目录
        String applicationPath = application.getContextPath();
        writer.print("contextPath=" + applicationPath);

        writer.print("<br>");

        // 获取文件的绝对路径
        String realPath = application.getRealPath("index.jsp");
        writer.print("index.jsp's real path is " + realPath);

        // 记录日志
        application.log("hello,我是日志。");

        writer.print("<br>");

        // 设置属性
        application.setAttribute("attr", "attr value");
        writer.print("attr: " + application.getAttribute("attr"));
    }
}
