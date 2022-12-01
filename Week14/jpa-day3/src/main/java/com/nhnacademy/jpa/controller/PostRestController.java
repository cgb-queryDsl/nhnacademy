package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.repository.PostDto;
import com.nhnacademy.jpa.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<PostDto> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }
}
