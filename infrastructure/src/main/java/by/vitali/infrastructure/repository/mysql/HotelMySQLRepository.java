package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.repository.HotelRepository;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
    public List<Hotel> getHotelsByTypeOfMeals(final TypeOfMeals typeOfMeals) throws HibernateException {
        final Session session = getSession();
        final String hql = "SELECT H FROM Hotel H WHERE type_of_meals=:type_of_meals";
        final Query query = session.createQuery(hql);
        query.setParameter("type_of_meals", typeOfMeals.toString());
        return (List<Hotel>) query.list();
    }

    @Override
    public List<Hotel> getHotelsByHotelCategory(final HotelCategory hotelCategory) throws HibernateException {
        final Session session = getSession();
        final String hql = "SELECT H FROM Hotel H WHERE category=:category";
        final Query query = session.createQuery(hql);
        query.setParameter("category", hotelCategory);
        return (List<Hotel>) query.list();
    }

    @Override
    public List<Hotel> getHotels() throws HibernateException {
        final Session session = getSession();
        final String hql = "FROM Hotel";
        final Query query = session.createQuery(hql);
        return (List<Hotel>) query.list();
    }
}
