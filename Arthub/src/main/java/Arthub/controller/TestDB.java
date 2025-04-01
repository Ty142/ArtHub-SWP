package Arthub.controller; // Or your appropriate package name

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
// No need for RestController import in this configuration class
// import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@Configuration
public class TestDB {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Arthub?serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("duy khang");
        return dataSource;
    }

    public static void main(String[] args) throws Exception {
        // Test the DataSource
        DataSource ds = new TestDB().dataSource();
        System.out.println("DataSource created successfully");
        // Perform database operations here
        String database = "select * from user";
        try (java.sql.Connection connection = ds.getConnection();
             java.sql.Statement statement = connection.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(database)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("LastName"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Database operations completed successfully");
        }
    }
}