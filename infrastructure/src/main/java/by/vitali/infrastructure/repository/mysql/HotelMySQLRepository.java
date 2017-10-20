package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.repository.HotelRepository;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The implementation repository for a hotel.
 */
@Repository
public class HotelMySQLRepository extends GeneralMySQLRepository<Hotel> implements HotelRepository {

    @Autowired
    public HotelMySQLRepository(final HibernateSessionManager sessionManager) {
        super(sessionManager);
    }


    @Override
    public List<Hotel> getHotelsByTypeOfMeals(final TypeOfMeals typeOfMeals) throws DaoException {
        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT H FROM Hotel H WHERE type_of_meals=:type_of_meals";
            final Query query = session.createQuery(hql);
            query.setParameter("type_of_meals", typeOfMeals.toString());
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
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT H FROM Hotel H WHERE category=:category";
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
