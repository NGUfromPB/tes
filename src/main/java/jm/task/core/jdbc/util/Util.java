package jm.task.core.jdbc.util;
import org.hibernate.SessionFactory;

import java.sql.*;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/kattes";
    private static final String U = "root";
    private static final String P = "root";

    private static Connection connection;

    public static Connection getConnection() {
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
