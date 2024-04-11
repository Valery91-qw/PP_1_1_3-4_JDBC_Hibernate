package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl dataAccesObject = new UserDaoJDBCImpl();
    
    public void createUsersTable() {
        dataAccesObject.createUsersTable();
    }

    public void dropUsersTable() {
        dataAccesObject.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dataAccesObject.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
