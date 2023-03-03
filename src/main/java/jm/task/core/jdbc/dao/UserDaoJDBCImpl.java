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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS" +
                    "(id mediumint not null auto_increment," +
                    "name VARCHAR(50)," +
                    "lastname VARCHAR(50)," +
                    "age tinyint," +
                    "PRIMARY KEY (id))");
            System.out.println("Успешно: создание таблицы");
        } catch (SQLException e) {
            System.out.println("SQLEx " + e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS USERS");
            System.out.println("Успешно: удаление таблицы");
        } catch (SQLException e) {
            System.out.println("SQLEx " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем := " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("SQLEx " + e);
        }
    }
    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM USERS WHERE id";
            statement.executeUpdate(sql);
            System.out.println("User удален");
        } catch (SQLException e) {
            System.out.println("SQLEx " + e);
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

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
        String sql = "TRUNCATE USERS";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("SQLEx " + e);
        }
    }
}
// логика транзакций по типу возврашение к коммиту? я совсем не понимаю как это можно сделать
