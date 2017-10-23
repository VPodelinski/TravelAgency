package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.repository.GeneralRepository;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Common class for other repositories.
 *
 * @param <T>
 */

public class GeneralMySQLRepository<T> implements GeneralRepository<T> {

    final private HibernateSessionManager sessionManager;

    public GeneralMySQLRepository(final HibernateSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    protected Session getSession() {
        return sessionManager.getSession();
    }

    @Override
    public T save(final T t) throws DaoException {
        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public T update(final T t) throws DaoException {

        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return t;
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public T delete(final T t) throws DaoException {

        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return t;
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public T read(final long id, final Class<T> type) throws DaoException {
        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT T FROM " + type.getName() + " T WHERE T.id=:id";
            final Query query = session.createQuery(hql);
            query.setParameter("id", id);
            transaction.commit();
            return (T) query.uniqueResult();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<T> getAll(final Class<T> type) throws DaoException {
        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            final String hql = "FROM " + type.getName();
            final Query query = session.createQuery(hql);
            transaction.commit();
            return (List<T>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }
}
