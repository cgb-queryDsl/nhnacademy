package com.nhnacademy.board.controller.post.form;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class PostViewFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        PostRepository postRepository = (PostRepository) servletContext.getAttribute("postRepository");

        String postId = req.getParameter("id");
//        log.info("postId = {}", postId);

        Post post = postRepository.getPost(Long.parseLong(postId));

        req.setAttribute("post", post);
//        log.info("게시글 아이디를 가지고 불러온 post 객체 = {}", post);

        return "/post/postView.jsp";
    }
}
