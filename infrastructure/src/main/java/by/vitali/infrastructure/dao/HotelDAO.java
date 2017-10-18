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
    public List<Hotel> getHotelsByTypeOfMeals(final TypeOfMeals typeOfMeals) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT H FROM Hotel H WHERE H.type_of_meals=:type_of_meals";
            final Query query = session.createQuery(hql);
            query.setParameter("type_of_meals", typeOfMeals);
            transaction.commit();
            return (List<Hotel>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Hotel> getHotelsByHotelCategory(final HotelCategory hotelCategory) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT H FROM Hotel H WHERE H.category=:category";
            final Query query = session.createQuery(hql);
            query.setParameter("category", hotelCategory);
            transaction.commit();
            return (List<Hotel>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }
}
