package com.shiqidu.oa.web.action;

import com.shiqidu.oa.bean.User;
import com.shiqidu.oa.utils.DBUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.PreparedStatement;

@WebServlet({"/user/list", "/user/detail", "/user/delete", "/user/save", "/user/edit", "/user/update"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/user/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/user/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/user/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/user/edit".equals(servletPath)) {
            doEdit(request, response);
        } else if ("/user/update".equals(servletPath)) {
            doUpdate(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询数据库
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 用户集合
        List<User> users = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            String sql = "select id,name,age,sex from learnjava.user";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String age = rs.getString("age");
                String sex = rs.getString("sex");

                // 将以上数据封装成对象
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
                // 放到集合中
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        // 把用户列表放到请求域中
        request.setAttribute("userList", users);

        // 转发到jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数
        String id = request.getParameter("id");
        // 查询数据库
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = new User();
        try {
            connection = DBUtil.getConnection();
            String sql = "select id,name,age,sex from learnjava.user where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String sex = rs.getString("sex");
                // 赋值bean
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        // 把用户列表放到请求域中
        request.setAttribute("user", user);
        // 转发到jsp
        request.getRequestDispatcher("/detail.jsp").forward(request, response);
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取部门编号
        String id = request.getParameter("id");
        // 链接数据库删除数据
        Connection connection = null;
        PreparedStatement ps = null;
        // 删除行数
        int deleteCount = 0;
        try {
            connection = DBUtil.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            String sql = "delete from learnjava.user where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            deleteCount = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, null);
        }

        // 删除结果
        if (deleteCount == 1) {
            // 调回到列表页
            response.sendRedirect(request.getContextPath() + "/user/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");

        Connection connection = null;
        PreparedStatement ps = null;
        int saveCount = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into learnjava.user(name,age,sex) values(?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, age);
            ps.setString(3, sex);
            // 保存成功数量
            saveCount = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, null);
        }

        if (saveCount == 1) {
            response.sendRedirect(request.getContextPath() + "/user/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数
        String id = request.getParameter("id");
        // 链接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = new User();
        try {
            conn = DBUtil.getConnection();
            String sql = "select id,name,age,sex from learnjava.user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String sex = rs.getString("sex");
                // 赋值bean
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        // 把用户列表放到请求域中
        request.setAttribute("user", user);
        // 转发到jsp
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取参数
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");

        Connection connection = null;
        PreparedStatement ps = null;
        int saveCount = 0;
        try {
            connection = DBUtil.getConnection();
            String sql = "update learnjava.user set name=?,age=?,sex=? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, age);
            ps.setString(3, sex);
            ps.setString(4, id);
            // 保存成功数量
            saveCount = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, null);
        }

        if (saveCount == 1) {
            response.sendRedirect(request.getContextPath() + "/user/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/list");
        }
    }
}
