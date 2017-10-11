package by.vitali.infrastructure.dao;

import by.vitali.infrastructure.dao.interfaces.ITourDAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TourDAO extends AbstractDAO<Tour> implements ITourDAO {

    @Override
    public Tour find(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        Tour tour = null;
        try {
            String hql = "SELECT T FROM Tour T WHERE T.id=:id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            tour = (Tour) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return tour;
    }

    @Override
    public List<Tour> getAll() throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Tour> tours = null;
        try {
            String hql = "FROM Tour";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            tours = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return tours;
    }

    @Override
    public List<Tour> getToursByRequest(String request) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Tour> tours = null;
        try {
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(request);
            tours = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return tours;
    }

    @Override
    public List<Tour> getToursWithLimit(int start, int size) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Tour> tours = null;
        try {
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Tour");
            query.setFirstResult(start);
            query.setMaxResults(size);
            tours = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return tours;
    }

    @Override
    public int getCountTours() throws DaoException {
        Session session = null;
        Transaction transaction = null;
        long count = 0;
        try {
            String hql = "SELECT COUNT(DISTINCT T.id) FROM Tour T";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            count = (long) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return (int) count;
    }

}
