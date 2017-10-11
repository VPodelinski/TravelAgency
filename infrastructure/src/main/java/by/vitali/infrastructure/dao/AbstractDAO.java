package by.vitali.infrastructure.dao;

import by.vitali.infrastructure.dao.interfaces.DAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDAO<T> implements DAO<T> {

    @Override
    public T save(T t) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return t;
    }

    @Override
    public T update(T t) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return t;
    }

    @Override
    public T delete(T t) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return t;
    }
}
