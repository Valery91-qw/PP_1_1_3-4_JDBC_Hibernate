package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserDaoHibernateImpl implements UserDao {
    private Util db = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS Users(id BIGINT NOT NULL AUTO_INCREMENT, name NVARCHAR(255), lastName NVARCHAR(255), age TINYINT, PRIMARY KEY (id));";
        try (Session session = db.hibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sqlQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlQuery = "DROP TABLE IF EXISTS Users;";
        try (Session session = db.hibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sqlQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = db.hibernateConnection().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = db.hibernateConnection().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = db.hibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("from User", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sqlQuery = "DELETE FROM Users;";
        try (Session session = db.hibernateConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sqlQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
