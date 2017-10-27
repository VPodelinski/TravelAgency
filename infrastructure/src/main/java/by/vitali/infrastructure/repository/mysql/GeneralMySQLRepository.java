package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.repository.GeneralRepository;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

        try {

            final Session session = getSession();
            session.save(t);

            return t;
        } catch (HibernateException e) {
            //logger.writeLog("Save entity error:" + e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public T update(final T t) throws DaoException {


        try {

            final Session session = getSession();
            session.update(t);

            return t;
        } catch (HibernateException e) {
            //logger.writeLog("Update entity error:" + e.getMessage());

            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public T delete(final T t) throws DaoException {


        try {
            final Session session = getSession();
            session.delete(t);

            return t;
        } catch (HibernateException e) {
            //logger.writeLog("Delete entity error:" + e.getMessage());

            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public T read(final long id, final Class<T> type) throws DaoException {

        try {

            final Session session = getSession();
            final String hql = "SELECT T FROM " + type.getName() + " T WHERE T.id=:id";
            final Query query = session.createQuery(hql);
            query.setParameter("id", id);

            return (T) query.uniqueResult();
        } catch (HibernateException e) {
            //logger.writeLog("Read entity error:" + e.getMessage());

            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<T> getAll(final Class<T> type) throws DaoException {

        try {

            final Session session = getSession();
            final String hql = "FROM " + type.getName();
            final Query query = session.createQuery(hql);

            return (List<T>) query.list();
        } catch (HibernateException e) {
            //logger.writeLog("Get all entities error:" + e.getMessage())

            throw new DaoException(e.getMessage());
        }
    }
}
