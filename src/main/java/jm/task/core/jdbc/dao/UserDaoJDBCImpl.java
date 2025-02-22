package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE db1.users (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT NOT NULL," +
                "PRIMARY KEY (id))";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sqlCreate);
            System.out.println("Table has been created!");
        } catch (SQLException e) {
            System.err.println("Table already exists!");}
        }

    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE db1.users";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
           statement.execute(sqlDrop);
           System.out.println("Table has been dropped!");
        } catch (SQLException e) {
           System.err.println("Table does not exist!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlSave = "INSERT INTO db1.users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sqlSave)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " has been saved!");
        } catch (SQLException e) {
            System.err.println("User already exists!");
        }
    }

    public void removeUserById(long id) {
        String sqlRemoveById = "DELETE FROM db1.users WHERE id = ?";
        try (Connection connection = Util.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User " + id + " has been deleted!");
        } catch (SQLException e) {
            System.err.println("User does not exist!");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sqlGetAll = "SELECT * FROM db1.users";
        try (Connection connection = Util.connect();  Statement statement = connection.createStatement() ){
            ResultSet resultSet = statement.executeQuery(sqlGetAll);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Users table does not exist!");
        }
        return users;
    }

    public void cleanUsersTable() {
        String sqlClean = "TRUNCATE TABLE db1.users";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sqlClean);
            System.out.println("Table has been cleaned!");
        } catch (SQLException e) {
           System.err.println("Table does not exist!");
        }
    }
}
