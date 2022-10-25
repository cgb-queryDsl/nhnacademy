package com.nhnacademy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(Objects.isNull(session)) {
            // 세션이 없으면 로그인화면
            resp.sendRedirect("/loginForm.html");
        } else {
            // 세션이 있으면 로그인 성공
            resp.sendRedirect("/init");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getServletConfig().getInitParameter("id");
        String pw = getServletConfig().getInitParameter("pw");

        String inputId = req.getParameter("id");
        String inputPw = req.getParameter("password");

        if (id.equals(null) || pw.equals(null)) {
            throw new NullPointerException("null id or pw");
        }

        if(id.equals(inputId) && pw.equals(inputPw)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);

            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/loginForm.html");
        }
    }
}
