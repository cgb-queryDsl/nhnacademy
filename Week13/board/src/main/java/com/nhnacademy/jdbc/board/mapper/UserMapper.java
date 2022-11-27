package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUser();
    void deleteById(Long id);
}
