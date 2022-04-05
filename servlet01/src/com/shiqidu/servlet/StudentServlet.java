package com.shiqidu.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置响应类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // 链接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/learnjava";
            String user = "root";
            String pass = "root";
            conn = DriverManager.getConnection(url, user, pass);
            // 获取预编译对象
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            // 执行SQL
            rs = ps.executeQuery();
            // 处理查询结果集
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int sex = rs.getInt("sex");
                //  System.out.println("id: " + id + ", name: " + name + ", age: " + age + ", sex: " + sex);
                out.print("id: " + id + ", name: " + name + ", age: " + age + ", sex: " + sex + "<br/>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            if (ps != null) {
                try {
                    ps.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            if (rs != null) {
                try {
                    rs.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
