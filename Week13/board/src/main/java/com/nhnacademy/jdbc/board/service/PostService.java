package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.Page;
import com.nhnacademy.jdbc.board.domain.dto.*;

import java.util.List;

public interface PostService {
    void registerPost(InsertPost insertPost);
    int getLastInsertPostId();
    Page<PostByCommunity> getAllPostsNotDeleted(PageSizeOffset pageSizeOffset);
    Page<PostByCommunity> getAllPosts(PageSizeOffset pageSizeOffset);
    ViewPost getViewPostData(Long postId);
    void deletePost(Long postId);
    void restorePost(Long postId);
    void modifyPost(PostUpdateForm postUpdateForm);

}
