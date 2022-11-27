package com.nhnacademy.jdbc.board.service.impl;

import com.nhnacademy.jdbc.board.domain.dto.InsertReply;
import com.nhnacademy.jdbc.board.domain.dto.ReplyByPost;
import com.nhnacademy.jdbc.board.domain.dto.UpdateReply;
import com.nhnacademy.jdbc.board.mapper.ReplyMapper;
import com.nhnacademy.jdbc.board.service.ReplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    public ReplyServiceImpl(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    @Override
    @Transactional
    public void registerReply(InsertReply insertReply) {
        replyMapper.insertReply(insertReply);
    }

    @Override
    public List<ReplyByPost> getAllRepliesNotDeleted(Long postId) {
        return replyMapper.getAllReplies(postId).stream()
                .filter((x) -> x.getDeleted().equals("N"))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReplyByPost> getAllReplies(Long postId) {
        return replyMapper.getAllReplies(postId);
    }

    @Override
    @Transactional
    public void deleteReply(Long replyId) {
        replyMapper.updateReplyDeletedToYes(replyId);
    }

    @Override
    @Transactional
    public void restoreReply(Long replyId) {
        replyMapper.updateReplyDeletedToNo(replyId);
    }

    @Override
    @Transactional(readOnly = true)
    public String getReply(Long replyId) {
        return replyMapper.getReplyById(replyId);
    }

    @Override
    @Transactional
    public void modifyReply(UpdateReply updateReply) {
        replyMapper.updateReplyById(updateReply);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getRepliesNotDeleted(Long postId) {
        return replyMapper.getRepliesCountNotDeleted(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getReplies(Long postId) {
        return replyMapper.getRepliesCount(postId);
    }
}
