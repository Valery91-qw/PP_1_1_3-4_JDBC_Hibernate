package jm.task.core.jdbc.util;
import java.sql.*;


public class Util {
    private String dbUrl = "jdbc:mysql://127.0.0.1:3306/";
    private String dbName = "Users";
    private String user = "root";
    private String password = "кщще123";

    private String sqlInitRequest = "create database if not exists Users;";

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
            // try (connection = DriverManager.getConnection(this.dbUrl, this.user, this.password)) {
            //     Statement statement = connection.createStatement();
            //     statement.execute(sqlInitRequest);
            // } catch (SQLException e) {
            //     throw e;
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public String getDBName () {
        return this.dbName;
    }
}
