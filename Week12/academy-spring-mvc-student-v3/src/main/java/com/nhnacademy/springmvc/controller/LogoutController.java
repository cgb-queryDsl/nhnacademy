package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");

        if (Objects.nonNull(sessionId) && sessionId.contains("on")) {
            session.removeAttribute("sessionId");
        }

        return "index";
    }
}
