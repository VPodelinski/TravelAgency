package by.vitali.infrastructure.dao;

import by.vitali.infrastructure.dao.interfaces.IUserDAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.RoleType;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public User find(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            String hql = "SELECT U FROM User U WHERE U.id=:id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    @Override
    public List<User> getAll() throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<User> users = null;
        try {
            String hql = "FROM User";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            users = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return users;
    }


    @Override
    public User getUserByEmail(String email) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            String hql = "SELECT U FROM User U WHERE U.email=:email";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    @Override
    public List<User> getUsersByRole(RoleType roleType) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<User> users = null;
        try {
            String hql = "SELECT U FROM User U WHERE U.role=:role";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("role", roleType);
            users =  query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return users;
    }
}
