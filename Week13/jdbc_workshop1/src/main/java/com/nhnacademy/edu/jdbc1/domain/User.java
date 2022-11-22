package com.nhnacademy.edu.jdbc1.domain;

import java.util.Date;

public class User {
    private Long id;
    private String username;
    private String password;
    private Date createAt;

    public User(Long id, String username, String password, Date createAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
