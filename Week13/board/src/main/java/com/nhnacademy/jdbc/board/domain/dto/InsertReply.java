package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertReply {
    private final Long postId;
    private final Long userId;
    private final String comment;

    public InsertReply(Long postId, Long userId, String comment) {
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
    }
}
