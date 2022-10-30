package com.nhnacademy.board.controller.post.form;

import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostUpdateFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("updateId", req.getParameter("updateId"));
        return "/post/postUpdate.jsp";
    }
}
