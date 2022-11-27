package com.nhnacademy.jdbc.board.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AdminInterceptor implements HandlerInterceptor {

    private final String SESSION = "session";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String session = (String) request.getSession().getAttribute(SESSION);

        if (Objects.isNull(session)) {
            response.sendRedirect("/");
            return false;
        } else {
            if (!session.contains("커뮤니티 관리자")) {
                response.sendRedirect("/noAccess");
                return false;
            }
        }
        return true;
    }
}
