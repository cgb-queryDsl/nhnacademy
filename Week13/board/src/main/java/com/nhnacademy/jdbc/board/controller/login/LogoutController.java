package com.nhnacademy.jdbc.board.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LogoutController {

    private final String SESSION = "session";

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SESSION);
        return "index/index";
    }
}
