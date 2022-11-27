package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReplyByPost {

    private final Long id;
    private final Long userId;
    private final String writer;
    private final String comment;
    private final Date replyTime;
    private final String deleted;

    public ReplyByPost(Long id, Long userId, String writer, String comment, Date replyTime, String deleted) {
        this.id = id;
        this.userId = userId;
        this.writer = writer;
        this.comment = comment;
        this.replyTime = replyTime;
        this.deleted = deleted;
    }
}
