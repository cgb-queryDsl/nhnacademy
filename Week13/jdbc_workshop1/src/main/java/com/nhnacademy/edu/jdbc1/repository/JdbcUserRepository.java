package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.DBUtils;
import com.nhnacademy.edu.jdbc1.domain.User;
import com.nhnacademy.edu.jdbc1.service.login.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class JdbcUserRepository implements UserRepository {

    public User getUser(Connection connection, String username) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM JdbcUsers where username=?");
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(rs.getLong(1),
                        rs.getString(2),
                        rs.getNString(3),
                        rs.getTimestamp(4));
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

}
