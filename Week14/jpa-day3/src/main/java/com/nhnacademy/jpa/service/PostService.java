package com.nhnacademy.jpa.service;

import com.nhnacademy.jpa.dto.PostByCommunity;
import com.nhnacademy.jpa.repository.PostDto;
import com.nhnacademy.jpa.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> getPosts(Pageable pageable) {
        return postRepository.getAllBy(pageable);
    }
}
