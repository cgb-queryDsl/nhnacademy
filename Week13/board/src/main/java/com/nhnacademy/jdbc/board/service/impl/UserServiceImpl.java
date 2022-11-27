package com.nhnacademy.jdbc.board.service.impl;

import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.mapper.UserMapper;
import com.nhnacademy.jdbc.board.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getLoginUser(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUser(Long id) {
        return userMapper.getUserById(id);
    }
}
