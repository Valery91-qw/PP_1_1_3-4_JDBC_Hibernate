package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    private String sqlCreateTable = "create table if not exists USERS(id BIGINT, name NVARCHAR(255), lastName NVARCHAR(255), age TINYINT);";
    private Util db = new Util();

    public UserDaoJDBCImpl() {
        
    }

    public void createUsersTable() {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.execute(sqlCreateTable);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("drop table if exists Users");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
