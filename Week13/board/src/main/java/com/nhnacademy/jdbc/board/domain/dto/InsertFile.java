package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertFile {
    private final Long postId;
    private final String filename;

    public InsertFile(Long postId, String filename) {
        this.postId = postId;
        this.filename = filename;
    }
}
