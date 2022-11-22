package com.nhnacademy.edu.jdbc1;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final DataSource DATASOURCE;

    private DBUtils(){}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://133.186.211.156:3306/nhn_academy_19", "nhn_academy_19",
                    "2/5l(ky6VP_bbyzZ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource() {
        return DATASOURCE;
    }

    static {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://133.186.211.156:3306/nhn_academy_19");
        basicDataSource.setUsername("nhn_academy_19");
        basicDataSource.setPassword("2/5l(ky6VP_bbyzZ");
        basicDataSource.setInitialSize(2);
        basicDataSource.setMaxTotal(2);
        basicDataSource.setTestOnBorrow(true);

        DATASOURCE = basicDataSource;
    }
}
