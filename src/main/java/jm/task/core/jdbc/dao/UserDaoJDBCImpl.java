package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    private String sqlCreateTable = "create table if not exists USERS(id BIGINT NOT NULL AUTO_INCREMENT, name NVARCHAR(255), lastName NVARCHAR(255), age TINYINT, PRIMARY KEY (id));";
    private Util db = new Util();

    public UserDaoJDBCImpl() {
        
    }

    public void createUsersTable() {
        try (Connection connection = db.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = db.connect(); Statement statement = connection.createStatement();) {
            statement.execute("drop table if exists Users");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = db.connect(); 
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users VALUES(null, ?, ?, ?)")
            ) {
            User user = new User(name, lastName, age);
            System.out.println(user.getAge());
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setByte(3, user.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(); 
        try (Connection connection = db.connect(); 
             PreparedStatement statement = connection.prepareStatement("select * from " + db.getDBName())
            ) {
            ResultSet obj = statement.executeQuery();
            while (obj.next()) {
                User curUser = new User();
                curUser.setId(obj.getLong(1));
                curUser.setName(obj.getString(2));
                curUser.setLastName(obj.getString(3));
                curUser.setAge(obj.getByte(4));
                users.add(curUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {

    }
}
