package com.shiqidu.oa.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 获取请求路径
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession(false);
        if ("/index.jsp".equals(servletPath) || "/welcome".equals(servletPath) || "/user/login".equals(servletPath) || "/user/logout".equals(servletPath)
                ||
                (session != null && session.getAttribute("username") != null)
        ) {
            // 继续往下走
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/welcome");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
