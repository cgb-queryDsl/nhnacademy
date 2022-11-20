package com.nhnmart.cs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
public class LogoutController {

    private static final String SESSION_ID = "sessionId";

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String sessionId = (String) session.getAttribute(SESSION_ID);

        if (isLogin(sessionId)) {
            session.removeAttribute(SESSION_ID);
        }

        return "index";
    }

    private boolean isLogin(String sessionId) {
        return Objects.nonNull(sessionId) && sessionId.contains("on");
    }
}
