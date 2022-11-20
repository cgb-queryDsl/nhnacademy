package com.nhnmart.cs.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = (String) request.getSession().getAttribute("sessionId");

        if(Objects.isNull(sessionId)) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

}