package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.sql.*;
import java.util.Map;
import java.util.Properties;

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

    public static void closeConnection() {
        if (connect() != null) {
           try {
               connect().close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
        }
    }
}

