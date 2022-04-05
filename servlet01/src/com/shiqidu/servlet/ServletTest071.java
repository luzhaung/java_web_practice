package com.shiqidu.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class ServletTest071 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        request.setAttribute("sysTime", date);

        // 请求转发
        // 1. 获取请求转发器
        RequestDispatcher dispatcher = request.getRequestDispatcher("/servlet072");
        // 2. 调用请求转发器的forward
        dispatcher.forward(request, response);
    }
}
