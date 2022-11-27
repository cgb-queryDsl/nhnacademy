package com.nhnacademy.jdbc.board.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {

    private final String SESSION = "session";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = (String) request.getSession().getAttribute(SESSION);

        if(Objects.isNull(sessionId)) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
