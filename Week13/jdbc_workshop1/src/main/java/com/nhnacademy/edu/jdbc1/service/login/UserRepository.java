package com.nhnacademy.edu.jdbc1.service.login;

import com.nhnacademy.edu.jdbc1.domain.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserRepository {
    User getUser(Connection connection, String username) throws SQLException;
}
