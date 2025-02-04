package controller;

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
        dataSource.setUrl("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=ArtHub;encrypt=true;trustServerCertificate=true;");
        dataSource.setUsername("sa");
        dataSource.setPassword("123123");
        return dataSource;
    }
}
