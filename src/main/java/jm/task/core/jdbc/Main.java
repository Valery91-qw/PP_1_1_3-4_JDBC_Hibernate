package jm.task.core.jdbc;
import java.sql.SQLException;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Jhon", "Doe", (byte) 10);
        userServiceImpl.saveUser("Jhon", "Sina", (byte) 20);
        userServiceImpl.saveUser("Gretta", "Tunenberg", (byte) 30);
        userServiceImpl.saveUser("Alan", "Walker", (byte) 40);
        List<User> users = userServiceImpl.getAllUsers();
        for(User user: users) {
            System.out.println(user.toString());
        }
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
