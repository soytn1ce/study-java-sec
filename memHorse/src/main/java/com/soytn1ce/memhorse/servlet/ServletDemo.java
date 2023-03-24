package com.soytn1ce.memhorse.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet" , name = "ServletDemo")
public class ServletDemo implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet初始化");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("获取config");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet 开始service");
    }

    @Override
    public String getServletInfo() {
        System.out.println("获取servlet info");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("servlet 销毁");
    }
}
