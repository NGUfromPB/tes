package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String craft = "CREATE TABLE IF NOT EXISTS USERS" +
                "  id       BIGINT       PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "  name     VARCHAR(250) DEFAULT NULL," +
                "  lastname VARCHAR(250) DEFAULT NULL," +
                "  age      TINYINT      DEFAULT NULL)";
        try (Statement statement =  connection.createStatement()) {
            statement.executeUpdate(craft);
        } catch (SQLException e) {
            System.out.println("SQLEX" + e);

        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS USERS");
        } catch (SQLException e) {
            System.out.println("SQLEX" + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USERS (name, lastname,age) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS WHERE user_id = ?")) {
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hhuser");

            List<User> users = new ArrayList<>();
            User abuser = new User();
            while (resultSet.next()) {

                abuser.setId((long) resultSet.getInt("id"));
                abuser.setName(String.valueOf(resultSet.getInt("name")));
                abuser.setLastName(String.valueOf(resultSet.getInt("lastname")));
                abuser.setAge((byte) resultSet.getInt("age"));
                users.add(abuser);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE USERS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
