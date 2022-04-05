package com.shiqidu.oa.web.action;

import com.shiqidu.oa.bean.Manager;
import com.shiqidu.oa.utils.DBUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.sql.ResultSet;
import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }
        if (username != null && password != null) {
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
                response.sendRedirect(request.getContextPath() + "/user/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
