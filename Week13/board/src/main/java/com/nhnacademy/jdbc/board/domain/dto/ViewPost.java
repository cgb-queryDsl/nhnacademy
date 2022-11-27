package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ViewPost {
    private final Long id;
    private final String title;
    private final String content;
    private final Date writeTime;
    private final String writer;

    public ViewPost(Long id, String title, String content, Date writeTime, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writeTime = writeTime;
        this.writer = writer;
    }
}
