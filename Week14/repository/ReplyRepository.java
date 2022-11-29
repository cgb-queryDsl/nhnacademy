package com.nhnacademy.jdbc.board.repository;

import com.nhnacademy.jdbc.board.domain.dto.ReplyByPost;
import com.nhnacademy.jdbc.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<ReplyByPost> getAllByPostId(Long postId);
    String getReplyById(Long replyId);
}
