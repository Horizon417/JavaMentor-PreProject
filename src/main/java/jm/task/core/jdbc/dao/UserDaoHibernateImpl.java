package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


//    @Override
//    public void createUsersTable() {
//        Transaction transaction = null;
//        String sql = "CREATE TABLE IF NOT EXISTS public.Users " +
//                "(id SERIAL, " +
//                " name VARCHAR(255), " +
//                " lastname VARCHAR(255), " +
//                " age INTEGER, " +
//                " PRIMARY KEY ( id ))";
//        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.createSQLQuery(sql);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS public.Users " +
                "(id SERIAL, " +
                " name VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @Override
//    public void dropUsersTable() {
//        Transaction transaction = null;
//        String sql = "DROP TABLE IF EXISTS public.users";
//        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.createSQLQuery(sql);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//
//    }
    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS public.users";
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        Transaction transaction = null;
//        User user = new User(name, lastName, age);
//        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(user);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @Override
//    public void removeUserById(long id) {
//        Transaction transaction = null;
//        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            User user = session.find(User.class, id);
//            session.delete(user);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//
//    }
    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public List<User> getAllUsers() {
//        Transaction transaction = null;
//        List<User> userList = null;
//        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            userList = session.createQuery("from User", User.class).list();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//        return userList;
//    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            userList = session.createQuery("FROM User", User.class).list();
            session.getTransaction().commit();
        }
        return userList;
    }

//    @Override
//    public void cleanUsersTable() {
//        Transaction transaction = null;
//        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            List<User> users = session.createQuery("from User", User.class).list();
//            for (User u : users) {
//                session.delete(u);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
