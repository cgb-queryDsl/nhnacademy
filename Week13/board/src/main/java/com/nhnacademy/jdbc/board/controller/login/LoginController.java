package com.nhnacademy.jdbc.board.controller.login;

import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final String SESSION = "session";
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String login(@RequestParam("id") String loginId,
                        @RequestParam("pw") String loginPw, HttpSession session) {

        Optional<User> optional = userService.getLoginUser(loginId);
        try {
            if (optional.isPresent() && optional.get().getPassword().equals(loginPw)) {
                session.setAttribute(SESSION, optional.get().getId() + "-" + optional.get().getNickname() + "-on");

                return "redirect:/community?page=1";
            }
        } catch (NoSuchElementException e) {
            return "index/index";
        }

        return "index/index";
    }
}
