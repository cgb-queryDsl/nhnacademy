package com.nhnacademy.board.controller.login;

import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/login/loginForm.jsp";
    }
}
