package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtils {
    static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=ArtHub;encrypt=true;trustServerCertificate=true;";
    static final String USER = "sa";
    static final String PASS = "123123";
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS );
            return conn;
        } catch (SQLException ex) {
            return null;
        }
    }
}