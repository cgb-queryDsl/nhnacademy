package com.nhnacademy.jdbc.board.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final String nickname;
    private final Date createdAt;

    public User(Long id, String username, String password, String nickname, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }
}
