package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.dto.PostByCommunity;
import com.nhnacademy.jpa.dto.QPostByCommunity;
import com.nhnacademy.jpa.entity.Post;
import com.nhnacademy.jpa.entity.QPost;
import com.nhnacademy.jpa.entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    public PostRepositoryImpl() {
        super(Post.class);
    }

    @Override
    public PostByCommunity getPost(Long postId) {
        QPost post = QPost.post;
        QUser user = QUser.user;

        QPostByCommunity qPostByCommunity = new QPostByCommunity(post.id,
                                                                    post.title,
                                                                    post.content,
                                                                    post.createdAt,
                                                                    user.nickName);

        return from(post)
                .innerJoin(post.user, user)
                .select(qPostByCommunity)
                .where(post.id.eq(postId))
                .fetchOne();
    }
}
