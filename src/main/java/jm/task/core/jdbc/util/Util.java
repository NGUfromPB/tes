package jm.task.core.jdbc.util;
import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;
import java.net.URL;
import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/kattes";
    private static final String U = "root";
    private static final String P = "1234";

    private Connection connection;
    private SessionFactory sessionFactory;
    public Util() {
        try {
            Connection connection = DriverManager.getConnection(URL + U + P);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public {
        sessionFactory = new Configuration().buildSessionFactory;
    }

}