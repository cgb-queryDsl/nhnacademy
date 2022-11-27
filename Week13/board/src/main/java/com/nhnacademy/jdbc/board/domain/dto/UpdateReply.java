package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class UpdateReply {

    private final Long userId;
    private final Long replyId;
    private final String comment;

    public UpdateReply(Long userId, Long replyId, String comment) {
        this.userId = userId;
        this.replyId = replyId;
        this.comment = comment;
    }
}
