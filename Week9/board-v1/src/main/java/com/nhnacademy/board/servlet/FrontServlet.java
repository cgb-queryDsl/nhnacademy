package com.nhnacademy.board.servlet;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.controller.EnController;
import com.nhnacademy.board.controller.KoController;
import com.nhnacademy.board.controller.login.LoginController;
import com.nhnacademy.board.controller.login.LoginFormController;
import com.nhnacademy.board.controller.login.LogoutController;
import com.nhnacademy.board.controller.post.form.PostRegisterFormController;
import com.nhnacademy.board.controller.post.form.PostServiceFormController;
import com.nhnacademy.board.controller.post.form.PostUpdateFormController;
import com.nhnacademy.board.controller.post.form.PostViewFormController;
import com.nhnacademy.board.controller.post.service.PostDeleteController;
import com.nhnacademy.board.controller.post.service.PostRegisterController;
import com.nhnacademy.board.controller.post.service.PostUpdateController;
import com.nhnacademy.board.controller.user.form.*;
import com.nhnacademy.board.controller.user.service.UserDeleteController;
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

        if("/users/user.do".equals(servletPath)) {
            command = new UserServiceFormController();
        } else if("/ko.do".equals(servletPath)) {
            command = new KoController();
        } else if ("/en.do".equals(servletPath)) {
            command = new EnController();
        } else if("/users/user/create.do".equals(servletPath)) {
            command = new UserCreateFormController();
        } else if("/users/user/update.do".equals(servletPath)) {
            command = new UserUpdateFormController();
        } else if ("/users/user/delete.do".equals(servletPath)) {
            command = new UserDeleteFormController();
        } else if ("/users/user/delete/check.do".equals(servletPath)) {
            command = new UserDeleteController();
        } else if("/users/user/get.do".equals(servletPath)) {
            command = new UserReadFormController();
        } else if("/login.do".equals(servletPath)) {
            command = new LoginFormController();
        } else if("/login/check.do".equals(servletPath)) {
            command = new LoginController();
        } else if("/logout.do".equals(servletPath)) {
            command = new LogoutController();
        } else if("/posts/post.do".equals(servletPath)) {
            command = new PostServiceFormController();
        } else if ("/posts/post/registerForm.do".equals(servletPath)) {
            command = new PostRegisterFormController();
        } else if ("/posts/post/view.do".equals(servletPath)) {
            command = new PostViewFormController();
        } else if ("/posts/post/register.do".equals(servletPath)) {
            command = new PostRegisterController();
        } else if ("/posts/post/updateForm.do".equals(servletPath)) {
            command = new PostUpdateFormController();
        } else if ("/posts/post/delete.do".equals(servletPath)) {
            command = new PostDeleteController();
        } else if ("/posts/post/update.do".equals(servletPath)) {
            command = new PostUpdateController();
        }

        return command;
    }
}
