package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    private String id;
    private String password;

    public static User createUser(String id, String password) {
        return new User(id, password);
    }

    private User(String id, String password) {
        this.id = id;
        this.password = password;
    }


}
