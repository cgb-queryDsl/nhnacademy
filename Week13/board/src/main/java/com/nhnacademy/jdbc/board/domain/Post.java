package com.nhnacademy.jdbc.board.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Post {
    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
    private final String deleted;

    public Post(Long id, String title, String content, Long userId, String deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.deleted = deleted;
    }
}
