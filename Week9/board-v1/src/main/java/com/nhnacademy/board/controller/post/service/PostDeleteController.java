package com.nhnacademy.board.controller.post.service;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.domain.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostDeleteController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        PostRepository postRepository = (PostRepository) servletContext.getAttribute("postRepository");

        long id = Long.parseLong(req.getParameter("id"));
        postRepository.remove(id);

        return "/post/postDeleteSuccess.jsp";
    }
}
