package com.nhnacademy.jpa.repository;

import com.nhnacademy.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUserName(String userName);
    void deleteById(Long id);
}


