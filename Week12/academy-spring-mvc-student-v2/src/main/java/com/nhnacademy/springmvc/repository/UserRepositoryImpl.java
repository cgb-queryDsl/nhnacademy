package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private static final List<User> userList = new ArrayList<>();

    @Override
    public User register(String id, String password) {
        User user = User.createUser(id, password);
        userList.add(user);
        log.info("userList = {}", userList);
        return user;
    }

    @Override
    public User getUser(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

}
