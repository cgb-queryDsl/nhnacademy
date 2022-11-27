package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.dto.InsertReply;
import com.nhnacademy.jdbc.board.domain.dto.ReplyByPost;
import com.nhnacademy.jdbc.board.domain.dto.UpdateReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    void insertReply(InsertReply insertReply);
    List<ReplyByPost> getAllReplies(Long postId);
    void updateReplyDeletedToYes(Long replyId);
    void updateReplyDeletedToNo(Long replyId);
    String getReplyById(Long replyId);
    void updateReplyById(UpdateReply updateReply);
    Long getRepliesCountNotDeleted(Long postId);
    Long getRepliesCount(Long postId);
}
