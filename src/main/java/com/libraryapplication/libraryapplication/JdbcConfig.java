package com.libraryapplication.libraryapplication;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class JdbcConfig {
    public static DataSource getMysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/library?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }
}