package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtils {
    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Arthub?serverTimezone=UTC&useSSL=false";
    String user = "root";
    String password = "duy khang";

    private static ConnectUtils instance;

    public ConnectUtils(){

    }

    public Connection openConection() throws Exception{
        Class.forName(driverName);
        Connection con = DriverManager.getConnection(url,user,password);
        return con;
    }
    public static ConnectUtils getInstance(){
        if (instance == null){
            instance = new ConnectUtils();
        }
        return  instance;
    }
}
