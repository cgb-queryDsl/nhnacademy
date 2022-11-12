package com.nhnacademy.servlet;

import com.nhnacademy.domain.MapStudentRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 서블릿 시작시 작동
        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("studentRepository", new MapStudentRepository());
    }

}
