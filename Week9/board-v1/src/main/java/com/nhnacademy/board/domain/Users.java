package com.nhnacademy.board.domain;

public class Users implements User {

    private String id;
    private String password;
    private String name;
    private String profileFileName;

    public Users(String id, String password, String name, String profileFileName) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileFileName = profileFileName;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getProfileFileName() {
        return this.profileFileName;
    }

    @Override
    public void setProfileFileName(String profileFileName) {
        this.profileFileName = profileFileName;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", profileFileName='" + profileFileName + '\'' +
                '}';
    }
}
