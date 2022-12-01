package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends PostRepositoryCustom, JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    @Query("select p from Post p where p.deleted = ?1")
    List<Post> findAllByPost(String deleted);

    int countBy();

    @Modifying
    @Query("update Post p set p.title = ?1 where p.id = ?2")
    void updatePost(String title, Long id);

    @Modifying
    @Query("update Post p set p.deleted = ?1 where p.id = ?2")
    void updatePostDeletedToYes(String deleted, Long id);

    List<PostDto> getAllBy(Pageable pageable);
}
