package com.nhnacademy.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class LoginController implements Command {

    private ServletContext context;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if(Objects.nonNull(session)) {
            return "/loginForm.jsp";
        }

        context = req.getServletContext();
        String id = (String) context.getAttribute("id");
        String pw = (String) context.getAttribute("pw");
        String inputId = req.getParameter("id");
        String inputPw = req.getParameter("password");

        if(id.equals(inputId) && pw.equals(inputPw)) {
            session = req.getSession();
            context.setAttribute("money", 20000);
            context.setAttribute("loginStatus", "on");
            return "/index.jsp";
        } else {
            return "redirect:/loginForm.jsp";
        }

    }

}
