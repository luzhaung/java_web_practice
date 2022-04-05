package com.shiqidu.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends GenericServlet {
    @Override
    public void init() {
        System.out.println("LoginServlet's init");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletConfig config = this.getServletConfig();
        System.out.println(config);
    }
}
