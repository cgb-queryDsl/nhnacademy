package com.nhnacademy.servlet.filter;

import javax.servlet.*;
import java.io.IOException;

public class CounterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletContext servletContext = request.getServletContext();
        int counter = (int) servletContext.getAttribute("counter");
        servletContext.setAttribute("counter", ++counter);

        chain.doFilter(request, response);      // 필수
    }
}
