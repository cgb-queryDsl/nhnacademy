package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class PostUpdateForm {
    private final Long postId;
    private final String title;
    private final String content;
    @Setter
    private Long userId;

    public PostUpdateForm(Long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }
}
