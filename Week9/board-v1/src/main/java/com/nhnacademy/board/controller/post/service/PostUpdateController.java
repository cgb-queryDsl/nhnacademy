package com.nhnacademy.board.controller.post.service;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
public class PostUpdateController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();;
        PostRepository postRepository = (PostRepository) servletContext.getAttribute("postRepository");
        long id = Long.parseLong(req.getParameter("id").toString());

        Post post = postRepository.getPost(id);
        post.setTitle(req.getParameter("title"));
        post.setContent(req.getParameter("content"));
        post.setWriteTime(LocalDateTime.now());

        postRepository.modify(post);

        return "/post/postUpdateSuccess.jsp";
    }
}
