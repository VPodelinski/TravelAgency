package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.repository.CommonRepository;
import by.vitali.infrastructure.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

/**
 * Common class for other repositories.
 *
 * @param <T>
 */
public class CommonMySQLRepository<T> implements CommonRepository<T> {

    protected SessionFactory sessionFactory;

    /**
     * This class return session.
     * @return
     */
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * @param t
     * @return
     * @throws DaoException
     */
    @Override
    public T save(T t) throws DaoException {
        Transaction transaction = null;
        try (final Session session = currentSession()) {
            transaction = session.beginTransaction();
            session.save(t);
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

    /**
     * @param t
     * @return
     * @throws DaoException
     */
    @Override
    public T update(T t) throws DaoException {

        Transaction transaction = null;
        try (final Session session = currentSession()) {
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

    /**
     * @param t
     * @return
     * @throws DaoException
     */
    @Override
    public T delete(final T t) throws DaoException {

        Transaction transaction = null;
        try (final Session session = currentSession()) {
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

    /**
     * @param id
     * @param type
     * @return
     * @throws DaoException
     */
    @Override
    public T read(final long id, final Class<T> type) throws DaoException {
        Transaction transaction = null;
        try (final Session session = currentSession()) {
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

    /**
     * @param type
     * @return
     * @throws DaoException
     */
    @Override
    public List<T> getAll(final Class<T> type) throws DaoException {
        Transaction transaction = null;
        try (final Session session = currentSession()) {
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
