package com.nhnacademy.edu.jdbc1.service.login;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserLoginService {
    boolean login(Connection connection, String username, String pwd) throws SQLException;
}
