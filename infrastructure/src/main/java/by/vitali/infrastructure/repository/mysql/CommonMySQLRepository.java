package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.repository.CommonRepository;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Common class for other repositories.
 *
 * @param <T>
 */
public class CommonMySQLRepository<T> implements CommonRepository<T> {

    private HibernateSessionManager sessionManager;

    public CommonMySQLRepository(final HibernateSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    protected Session getSession() {
        return sessionManager.getSession();
    }

    @Override
    public T save(T t) throws DaoException {
        try (final Session session = getSession()) {
            session.save(t);
            return t;
        } catch (HibernateException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public T update(T t) throws DaoException {

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
            final String hql = "SELECT H FROM " + type.getName() + " H WHERE H.id=:id";
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
