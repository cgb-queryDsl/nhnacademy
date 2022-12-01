package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.dto.PostByCommunity;
import com.nhnacademy.jpa.entity.Post;
import com.nhnacademy.jpa.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findById() {
        Optional<Post> post = postRepository.findById(1l);

        Assertions.assertThat(post.get().getId()).isEqualTo(1l);
    }

    @Test
    void insert() {
        Post post = new Post();
        post.setId(2l);
        post.setTitle("게시글 제목");
        post.setContent("게시글 내용");
        post.setCreatedAt(LocalDateTime.now());
        post.setDeleted("N");

        Optional<User> user = userRepository.findById(2l);

        post.setUser(user.get());

        postRepository.save(post);
        postRepository.flush();
    }

    @Test
    void findAll() {
        List<Post> posts = postRepository.findAll();

        Assertions.assertThat(posts.size()).isEqualTo(12);
    }

    @Test
    void findAllNotDeleted() {
        List<Post> posts = postRepository.findAllByPost("N");

        Assertions.assertThat(posts.size()).isEqualTo(11);
    }

    @Test
    void countPosts() {
        int count = postRepository.countBy();

        Assertions.assertThat(count).isEqualTo(12);
    }

    @Test
    void updatePost() {
        postRepository.updatePost("제목 변경", 1l);
        Optional<Post> post = postRepository.findById(1l);

        Assertions.assertThat(post.get().getTitle()).isEqualTo("제목 변경");
    }

    @Test
    void updatePostDeletedToYes() {
        postRepository.updatePostDeletedToYes("Y", 1l);
        Optional<Post> post = postRepository.findById(1l);

        Assertions.assertThat(post.get().getDeleted()).isEqualTo("Y");
    }

    @Test
    void viewPost() {
        PostByCommunity post = postRepository.getPost(1l);

        Assertions.assertThat(post.getWriter()).isEqualTo("User 1");
    }
}