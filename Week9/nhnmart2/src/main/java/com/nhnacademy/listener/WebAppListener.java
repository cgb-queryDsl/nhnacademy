package com.nhnacademy.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class WebAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        // id, pw 설정
        servletContext.setAttribute("id", "abc");
        servletContext.setAttribute("pw", "123");

        // food 설정
        servletContext.setAttribute("product1", "onion-1000-2");
        servletContext.setAttribute("product2", "egg-2000-5");
        servletContext.setAttribute("product3", "greenOnion-500-10");
        servletContext.setAttribute("product4", "apple-2000-20");

        // login 설정
        servletContext.setAttribute("loginStatus", "off");

        // locale 설정
        servletContext.setAttribute("locale", "ko");
    }
}
