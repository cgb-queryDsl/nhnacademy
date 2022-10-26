package com.nhnacademy.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        ServletContext context = req.getServletContext();
        if(Objects.nonNull(session)) {
            String loginStatus = (String) context.getAttribute("loginStatus");
            context.setAttribute("loginStatus", "off");
            session.invalidate();
        } else if (Objects.isNull(session)) {
            return "/loginRequest.jsp";
        }

        return "/index.jsp";
    }
}
