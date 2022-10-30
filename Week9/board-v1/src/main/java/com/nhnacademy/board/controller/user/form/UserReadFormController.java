package com.nhnacademy.board.controller.user.form;

import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserReadFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/user/userRead.jsp";
    }
}
