package com.nhnacademy.servlet;

import com.nhnacademy.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            Command command = resolveServlet(req.getServletPath());
            String view = command.execute(req, resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception ex) {
            log.error("", ex);
            req.setAttribute("exception", ex);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    private Command resolveServlet(String servletPath) {
        Command command = null;

        if("/login.do".equals(servletPath)) {
            command = new LoginController();
        } else if ("/loginForm.do".equals(servletPath)) {
            command = new LoginFormController();
        } else if ("/logout.do".equals(servletPath)) {
            command = new LogoutController();
        } else if ("/init.do".equals(servletPath)) {
            command = new InitController();
        } else if ("/foods.do".equals(servletPath)) {
            command = new FoodController();
        } else if ("/pay.do".equals(servletPath)) {
            command = new PayController();
        } else if ("/money.do".equals(servletPath)) {
            command = new MoneyController();
        }

        return command;
    }
}
