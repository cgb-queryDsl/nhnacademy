package com.nhnacademy.board.controller.login;

import com.nhnacademy.board.controller.Command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();

        String adminLogin = (String) servletContext.getAttribute("adminLogin");
        String userLogin = (String) servletContext.getAttribute("userLogin");

        if(adminLogin.contains("on")) {
            servletContext.setAttribute("adminLogin", "off");
        } else if (userLogin.contains("on")) {
            servletContext.setAttribute("userLogin", "off");
        }

        return "/index.jsp";
    }
}
