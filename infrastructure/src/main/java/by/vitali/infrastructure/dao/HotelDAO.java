package by.vitali.infrastructure.dao;

import by.vitali.infrastructure.dao.interfaces.IHotelDAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HotelDAO extends AbstractDAO<Hotel> implements IHotelDAO {

    @Override
    public Hotel find(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        Hotel hotel = null;
        try {
            String hql = "SELECT H FROM Hotel H WHERE H.id=:id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            hotel = (Hotel) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return hotel;
    }

    @Override
    public List<Hotel> getAll() throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Hotel> hotels = null;
        try {
            String hql = "FROM Hotel";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            hotels = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return hotels;
    }

    @Override
    public List<Hotel> getHotelsByTypeOfMeals(TypeOfMeals typeOfMeals) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Hotel> hotels = null;
        try {
            String hql = "SELECT H FROM Hotel H WHERE H.type_of_meals=:type_of_meals";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("type_of_meals", typeOfMeals);
            hotels = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return hotels;
    }

    @Override
    public List<Hotel> getHotelsByHotelCategory(HotelCategory hotelCategory) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Hotel> hotels = null;
        try {
            String hql = "SELECT H FROM Hotel H WHERE H.category=:category";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("category", hotelCategory);
            hotels = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return hotels;
    }

}
