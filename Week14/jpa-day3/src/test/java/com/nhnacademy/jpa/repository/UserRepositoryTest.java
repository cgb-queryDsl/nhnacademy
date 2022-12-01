package com.nhnacademy.jpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import com.nhnacademy.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void insertUser() {
        User user = new User();
        user.setId(3l);
        user.setUserName("abc");
        user.setPassword("123");
        user.setNickName("user1");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
        userRepository.flush();
    }

    @Test
    void findById() {
        Optional<User> user = userRepository.findById(1l);

        Assertions.assertThat(user.get().getNickName()).isEqualTo("관리자");
    }

    @Test
    void findByUserName() {
        Optional<User> user = userRepository.findByUserName("aaa");

        Assertions.assertThat(user.get().getPassword()).isEqualTo("123");
    }

    @Test
    void deleteById() {
        userRepository.deleteById(2l);
    }

}