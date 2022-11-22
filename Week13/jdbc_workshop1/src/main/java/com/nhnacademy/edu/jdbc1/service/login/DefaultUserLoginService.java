package com.nhnacademy.edu.jdbc1.service.login;

import com.nhnacademy.edu.jdbc1.domain.User;
import com.nhnacademy.edu.jdbc1.repository.JdbcUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@Service
public class DefaultUserLoginService implements UserLoginService {

    @Override
    public boolean login(Connection connection, String id, String pwd) throws SQLException {
        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository();
        User user = jdbcUserRepository.getUser(connection, id);
        log.info("DefaultUserLoginService user = {}", user);

        if (Objects.nonNull(user) && user.getPassword().equals(pwd)) {
            return true;
        }

        return false;
    }
}
