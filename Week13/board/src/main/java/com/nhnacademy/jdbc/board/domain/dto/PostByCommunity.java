package com.nhnacademy.jdbc.board.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class PostByCommunity {
    private final Long id;
    private final String title;
    private final String nickname;
    private final Date writeTime;
    private final String deleted;
    private final Long userId;
    private Long commentCount;

    public PostByCommunity(Long id, String title, String nickname, Date writeTime, String deleted, Long userId) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.writeTime = writeTime;
        this.deleted = deleted;
        this.userId = userId;
    }
}
