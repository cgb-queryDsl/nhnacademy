package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Value;

@Value
public class PostValidation {
    private final String field;
    private final String message;

    public PostValidation(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
