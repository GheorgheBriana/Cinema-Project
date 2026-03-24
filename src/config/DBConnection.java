package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String url = "jdbc:mysql://localhost:3306/cinemaDB";
    private String user = "root";
    private String password = "root";
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
