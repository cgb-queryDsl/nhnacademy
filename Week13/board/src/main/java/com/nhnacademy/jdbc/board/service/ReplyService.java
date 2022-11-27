package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.dto.InsertReply;
import com.nhnacademy.jdbc.board.domain.dto.ReplyByPost;
import com.nhnacademy.jdbc.board.domain.dto.UpdateReply;

import java.util.List;

public interface ReplyService {
    void registerReply(InsertReply insertReply);
    List<ReplyByPost> getAllRepliesNotDeleted(Long postId);
    List<ReplyByPost> getAllReplies(Long postId);
    void deleteReply(Long replyId);
    void restoreReply(Long replyId);
    String getReply(Long replyId);
    void modifyReply(UpdateReply updateReply);
    Long getRepliesNotDeleted(Long postId);
    Long getReplies(Long postId);
}
