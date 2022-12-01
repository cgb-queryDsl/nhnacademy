package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.dto.PostByCommunity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PostRepositoryCustom {
    PostByCommunity getPost(Long postId);
}
