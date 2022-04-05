package com.shiqidu.oa.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class Manager implements HttpSessionBindingListener {
    private String username;
    private String password;

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 管理员登录
        ServletContext application = event.getSession().getServletContext();
        Object onlineUserCount = application.getAttribute("onlineUserCount");
        if (onlineUserCount == null) {
            application.setAttribute("onlineUserCount", 1);
        } else {
            int count = (Integer) onlineUserCount;
            count++;
            application.setAttribute("onlineUserCount", count);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 管理员退出
        ServletContext application = event.getSession().getServletContext();
        Integer onlineUserCount = (Integer) application.getAttribute("onlineUserCount");
        onlineUserCount--;
        application.setAttribute("onlineUserCount", onlineUserCount);
    }

    public Manager() {
    }

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
