package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.User;

public interface UserRepository {

    User register(String id, String password);
    User getUser(String id);
}
