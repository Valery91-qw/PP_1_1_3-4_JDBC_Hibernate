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
        Transaction transaction = null;
        try (Session session = db.hibernateConnection().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(UserDaoHibernateImplEnum.CREATE_TABLE.getQuery()).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null ) { 
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = db.hibernateConnection().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(UserDaoHibernateImplEnum.DROP_TABLE.getQuery()).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null ) { 
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = db.hibernateConnection().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null ) { 
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = db.hibernateConnection().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null ) { 
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Transaction transaction = null;
        try (Session session = db.hibernateConnection().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery(UserDaoHibernateImplEnum.GET_ALL_FROM_TABLE.getQuery(), User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null ) { 
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = db.hibernateConnection().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(UserDaoHibernateImplEnum.DELETE_ALL_FROM_TABLE.getQuery()).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null ) { 
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
