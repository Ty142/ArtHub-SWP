package Arthub.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@Configuration

public class TestDB {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://127.0.0.1:1433;databaseName=Arthub;encrypt=true;trustServerCertificate=true;");
        dataSource.setUsername("sa");
        dataSource.setPassword("123123");
        return dataSource;
    }
}
