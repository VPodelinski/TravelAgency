package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.repository.TourRepository;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TourMySQLRepository extends CommonMySQLRepository<Tour> implements TourRepository {

    // неопределенно!!!
    @Override
    public List<Tour> getToursByRequest(final String request) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery(request);
            transaction.commit();
            return (List<Tour>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Tour> getToursWithLimit(final int start, final int size) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final Query query = session.createQuery("FROM Tour");
            query.setFirstResult(start);
            query.setMaxResults(size);
            transaction.commit();
            return (List<Tour>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public int getCountTours() throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {

            transaction = session.beginTransaction();
            final String hql = "SELECT COUNT(DISTINCT T.id) FROM Tour T";
            final Query query = session.createQuery(hql);
            transaction.commit();
            return (int) query.uniqueResult();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }
}
