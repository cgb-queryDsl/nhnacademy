package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertUuid {
    private final Long fileId;
    private final Long postId;
    private final String uuidValue;

    public InsertUuid(Long fileId, Long postId, String uuidValue) {
        this.fileId = fileId;
        this.postId = postId;
        this.uuidValue = uuidValue;
    }
}

