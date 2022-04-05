package com.shiqidu.servlet;

import jakarta.servlet.*;
import jakarta.servlet.GenericServlet;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletTest04 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();

        ServletContext application = this.getServletContext();

        Object attr = application.getAttribute("attr");
        writer.print("attr: " + attr);
    }
}
