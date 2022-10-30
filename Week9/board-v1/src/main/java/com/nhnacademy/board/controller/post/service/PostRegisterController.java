package com.nhnacademy.board.controller.post.service;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.domain.PostRepository;
import com.nhnacademy.board.domain.Posts;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
public class PostRegisterController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        PostRepository postRepository = (PostRepository) servletContext.getAttribute("postRepository");
        String userLogin = (String) servletContext.getAttribute("userLogin");

        postRepository.register(new Posts(
                req.getParameter("title"),
                req.getParameter("content"),
                userLogin.split("-")[0],
                LocalDateTime.now()
        ));

        log.info("게시글 추가 후 postRepository={}", postRepository);

        return "/post/postRegisterSuccess.jsp";
    }
}
