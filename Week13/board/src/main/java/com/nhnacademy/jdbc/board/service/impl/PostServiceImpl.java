package com.nhnacademy.jdbc.board.service.impl;

import com.nhnacademy.jdbc.board.domain.Page;
import com.nhnacademy.jdbc.board.domain.dto.*;
import com.nhnacademy.jdbc.board.mapper.PostMapper;
import com.nhnacademy.jdbc.board.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    @Transactional
    public void registerPost(InsertPost insertPost) {
        postMapper.insertPost(insertPost);
    }

    @Override
    @Transactional(readOnly = true)
    public int getLastInsertPostId() {
        return postMapper.getLastInsertPostId();
    }

    @Override
    public Page<PostByCommunity> getAllPostsNotDeleted(PageSizeOffset pageSizeOffset) {
        List<PostByCommunity> post = postMapper.getAllPosts(pageSizeOffset).stream()
                                            .filter((x) -> x.getDeleted().equals("N"))
                                            .sorted(Comparator.comparing((PostByCommunity p) -> p.getId()).reversed())
                                            .collect(Collectors.toList());
        int totalCount = postMapper.totalPostCountNotDeleted();

        return new Page<>(post, totalCount);
    }

    @Override
    public Page<PostByCommunity> getAllPosts(PageSizeOffset pageSizeOffset) {
        List<PostByCommunity> post = postMapper.getAllPosts(pageSizeOffset).stream()
                                                .sorted(Comparator.comparing((PostByCommunity p) -> p.getId()).reversed())
                                                .collect(Collectors.toList());
        int totalCount = postMapper.totalPostCount();

        return new Page<>(post, totalCount);
    }

    @Override
    @Transactional(readOnly = true)
    public ViewPost getViewPostData(Long postId) {
        return postMapper.getViewPostById(postId);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        postMapper.updatePostDeletedToYes(postId);
    }

    @Override
    @Transactional
    public void restorePost(Long postId) {
        postMapper.updatePostDeletedToNo(postId);
    }

    @Override
    @Transactional
    public void modifyPost(PostUpdateForm postUpdateForm) {
        postMapper.updatePostById(postUpdateForm);
    }

}
