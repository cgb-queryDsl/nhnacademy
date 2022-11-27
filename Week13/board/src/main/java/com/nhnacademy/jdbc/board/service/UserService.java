package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getLoginUser(String username);
    Optional<User> getUser(Long id);
}
