package jm.task.core.jdbc.util;
import java.sql.*;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import jm.task.core.jdbc.model.User;

public class Util {
    private String dbUrl = "jdbc:mysql://127.0.0.1:3306/";
    private String dbName = "Users";
    private String user = "root";
    private String password = "кщще123";
    private String sqlInitRequest = "CREATE DATABASE IF NOT EXISTS Users;";

    public Connection connect() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                connection = DriverManager.getConnection(dbUrl, user, password);
                Statement statement = connection.createStatement();
                statement.execute(sqlInitRequest);
                connection = DriverManager.getConnection(dbUrl + dbName, user, password);
            } catch (SQLException e) {
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public SessionFactory hibernateConnection() {
        Properties prop = setHibernateProperties();
        SessionFactory sessionFactory = new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
        return sessionFactory;
    }

    private Properties setHibernateProperties() {
        Properties prop = new Properties();
        prop.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        prop.setProperty(Environment.URL, dbUrl + "users");
        prop.setProperty(Environment.USER, user);
        prop.setProperty(Environment.PASS, password);
        prop.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        prop.setProperty(Environment.SHOW_SQL, "true");
        prop.setProperty(Environment.FORMAT_SQL, "true");
        prop.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        return prop;
    }
}
