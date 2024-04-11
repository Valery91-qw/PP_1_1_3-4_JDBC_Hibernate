package jm.task.core.jdbc;
import java.sql.SQLException;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl obj = new UserServiceImpl();
        obj.createUsersTable();
    }
}
