package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/db1";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
           try {
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
        }
    }
}

