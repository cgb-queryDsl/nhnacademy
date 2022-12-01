package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends FileRepositoryCustom, JpaRepository<File, Long> {
    @Query("select F from File F where F.post.id = ?1")
    List<File> findByPostId(Long postId);
}
