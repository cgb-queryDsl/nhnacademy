package com.nhnacademy.board.listener;

import com.nhnacademy.board.domain.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.DataInputStream;
import java.io.File;
import java.time.LocalDateTime;

@Slf4j
@WebListener
public class WebAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String fileName = servletContext.getInitParameter("counterFilaName");
        File file = new File("resources/" + fileName);
        String filePath = file.getAbsolutePath();

        int visitCount = 0;

        servletContext.setAttribute("visitCount", visitCount);

        servletContext.setAttribute("locale", "ko");
        log.info("locale 초기 값 설정 = ko");

        User admin = new Users("admin", "12345", "관리자", "X");
        User user1 = new Users("abc", "123", "user1", "image1.jpeg");
        User user2 = new Users("qwe", "456", "user2", "image2.jpeg");
        servletContext.setAttribute("admin", admin);
        servletContext.setAttribute("adminLogin", "off");
        log.info("admin 계정 추가 admin = {}", admin);
        log.info("admin 계정 로그인 상태 = off");

        servletContext.setAttribute("userLogin", "off");
        log.info("일반 유저 로그인 상태 = off");

        UserRepository repository = new UsersRepository();
        repository.add(user1);
        repository.add(user2);
        servletContext.setAttribute("repository", repository);
        log.info("UserRepository 생성 = {}", repository);

        Post post1 = new Posts("Test 게시글", "테스트 용을 위한 게시글 입니다.",
                "Test Writer", LocalDateTime.now());

        Post post2 = new Posts("2번 게시글", "테스트 게시글입니다.", "Test Writer", LocalDateTime.now());
        PostRepository postRepository = new PostsRepository();
        postRepository.register(post1);
        postRepository.register(post2);
        servletContext.setAttribute("postRepository", postRepository);
        log.info("PostRepository 생성 = {}", postRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
