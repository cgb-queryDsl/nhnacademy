package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.User;
import com.nhnacademy.springmvc.domain.UserRequest;
import com.nhnacademy.springmvc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String loginForm(HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");

        if (Objects.nonNull(sessionId) && sessionId.equals("on"))
            return "index";

        return "loginForm";
    }

    @PostMapping
    public String login(@ModelAttribute UserRequest userRequest, HttpSession session) {
        User user = userRepository.getUser(userRequest.getId());

        if (user.getPassword().equals(userRequest.getPassword())) {
            session.setAttribute("sessionId", "on");
            return "loginSuccess";
        }

        return "loginFail";
    }
}
