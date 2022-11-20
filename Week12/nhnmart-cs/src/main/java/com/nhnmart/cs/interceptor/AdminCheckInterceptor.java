package com.nhnmart.cs.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    private static final String SESSION_ID = "sessionId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = (String) request.getSession().getAttribute(SESSION_ID);

        if (Objects.nonNull(sessionId) && !sessionId.contains("admin")){
            response.sendRedirect("/noAuthorized");
        }

        return true;
    }
}
