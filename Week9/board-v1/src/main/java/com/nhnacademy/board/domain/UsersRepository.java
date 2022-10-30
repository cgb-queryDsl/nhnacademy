package com.nhnacademy.board.domain;

import java.util.*;
import java.util.stream.Collectors;

public class UsersRepository implements UserRepository {

    private Map<String, User> repository;

    public UsersRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void add(User user) {
        this.repository.put(user.getId(), user);
    }

    @Override
    public void modify(User user) {
        for(String key : this.repository.keySet()) {
            if(user.getId().equals(this.repository.get(key))) {
                this.repository.replace(user.getId(), user);
                break;
            }
        }
    }

    @Override
    public User remove(String id) {
        User returnUser = null;

        for(String key : this.repository.keySet()) {
            if(this.repository.get(key).getId().equals(id)) {
                returnUser = this.repository.remove(key);
                break;
            }
        }

        return returnUser;
    }

    @Override
    public User getUser(String id) {
        User returnUser = null;

        for(String key : this.repository.keySet()) {
            if(this.repository.get(key).getId().equals(id)) {
                returnUser = this.repository.get(key);
                break;
            }
        }

        return returnUser;
    }

    @Override
    public Map<String, User> getUsers() {
        return this.repository;
    }

    @Override
    public String toString() {
        return "UsersRepository{" +
                "repository=" + repository +
                '}';
    }
}
