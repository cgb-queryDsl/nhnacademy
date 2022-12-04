package com.nhnacademy.jpa.config;

import com.nhnacademy.jpa.Base;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {

    @Bean
    public DataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        basicDataSource.setUrl("jdbc:mysql://133.186.151.141:3306/nhn_academy_19");
        basicDataSource.setUsername("nhn_academy_19");
        basicDataSource.setPassword("2/5l(ky6VP_bbyzZ");

        basicDataSource.setInitialSize(2);
        basicDataSource.setMaxTotal(10);

        return basicDataSource;
    }
}
