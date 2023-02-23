package jm.task.core.jdbc.util;
import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;
import java.net.URL;
import java.sql.*;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/kattes";
    private static final String U = "root";
    private static final String P = "1234";

    private Connection connection;

    public Connection getConnection() {
        connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL,U,P);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQLEx " + e);
        }
        return connection;
    }
}
