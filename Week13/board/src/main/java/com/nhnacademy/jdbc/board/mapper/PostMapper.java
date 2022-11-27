package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.Page;
import com.nhnacademy.jdbc.board.domain.Post;
import com.nhnacademy.jdbc.board.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    Optional<Post> getPostById(Long id);
    List<PostByCommunity> getAllPosts(PageSizeOffset pageSizeOffset);
    List<PostByCommunity> getAllPostsNotDeleted(PageSizeOffset pageSizeOffset);
    void insertPost(InsertPost insertPost);
    int getLastInsertPostId();
    ViewPost getViewPostById(Long id);
    void updatePostDeletedToYes(Long id);
    void updatePostDeletedToNo(Long id);
    void updatePostById(PostUpdateForm postUpdateForm);
    int totalPostCount();
    int totalPostCountNotDeleted();
}
