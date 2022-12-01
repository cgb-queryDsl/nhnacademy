package com.nhnacademy.jpa.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@ToString
public class PostByCommunity {

    private Long id;
    private String title;
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "nickname")
    private String writer;

    @QueryProjection
    public PostByCommunity(Long id, String title, String content, LocalDateTime createdAt, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.writer = writer;
    }
}
