package com.nhnacademy.jpa.repository;

public interface PostDto {
    String getTitle();
    String getContent();

    interface UserDto {
        String getNickName();
    }
}
