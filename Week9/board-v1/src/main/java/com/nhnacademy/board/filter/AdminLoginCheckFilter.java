package com.nhnacademy.board.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@WebFilter(filterName = "adminLoginCheckFilter",
        initParams = @WebInitParam(
                name = "exclude-urls",
                value = "/\n" +
                        "/login.do\n" +
                        "/logout.do\n" +
                        "/login/check.do\n" +
                        "/ko\n" +
                        "/en\n" +
                        "/posts/post/*"))
public class AdminLoginCheckFilter implements Filter {

    private Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");

        excludeUrls = Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
        ServletContext servletContext = request.getServletContext();
        String adminLogin = (String) servletContext.getAttribute("adminLogin");
        System.out.println("doFilter() call");

        if(adminLogin.contains("off")) {
            if(excludeUrls.contains( ((HttpServletRequest) request).getRequestURI())) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/noAuthority.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
        */
    }
}
