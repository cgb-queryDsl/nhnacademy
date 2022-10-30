package com.nhnacademy.board.domain;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    void add(User user);
    void modify(User user);
    User remove(String id);

    User getUser(String id);
    Map<String, User> getUsers();
}
