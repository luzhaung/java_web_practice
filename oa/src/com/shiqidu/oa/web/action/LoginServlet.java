package com.shiqidu.oa.web.action;

import com.shiqidu.oa.bean.Manager;
import com.shiqidu.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.sql.ResultSet;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;

@WebServlet({"/user/login", "/user/logout"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/user/logout".equals(servletPath)) {
            doLogout(request, response);
        }
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 从session中移除user对象
            session.removeAttribute("user");
            // 手动销毁session对象
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/error.html");
            return;
        }
        // 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from learnjava.manager where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                loginSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        if (loginSuccess) {
            // 获取session中的用户名用于前台显示当前登录用户
            HttpSession session = request.getSession();
            Manager manager = new Manager(username, password);
            session.setAttribute("user", manager);
            session.setAttribute("username", username);
            // 是否选择了十天免登陆
            String remember = request.getParameter("remember");
            if ("1".equals(remember)) {
                // 创建cookie对象
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                // 设置有效期
                cookie1.setMaxAge(86400 * 10);
                cookie2.setMaxAge(86400 * 10);
                // 设置path（整个应用）
                cookie1.setPath(request.getServletPath());
                cookie2.setPath(request.getServletPath());
                // 响应cookie给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            response.sendRedirect(request.getContextPath() + "/user/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}
