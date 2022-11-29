package com.nhnacademy.jdbc.board.repository;

import com.nhnacademy.jdbc.board.domain.dto.ViewPost;
import com.nhnacademy.jdbc.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> getPostById(Long id);
    ViewPost getViewPostById(Long id);
    int countAll();
}
