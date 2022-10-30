package com.nhnacademy.board.controller.user.service;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserDeleteController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        UserRepository repository = (UserRepository) servletContext.getAttribute("repository");

        User removeUser = repository.getUser(req.getParameter("id"));
        log.info("getUser removeUser = {}", removeUser);

        if (removeUser == null) {
            return "/user/userFail.jsp";
        } else {
            repository.remove(removeUser.getId());

            log.info("After remove() repository={}", repository);

            req.setAttribute("removeUser", removeUser);
        }

        return "/user/userDeleteSuccess.jsp";
    }
}
