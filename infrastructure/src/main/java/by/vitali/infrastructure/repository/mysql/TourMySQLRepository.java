package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.*;
import by.vitali.infrastructure.repository.TourRepository;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The implementation repository for a tour.
 */
@Repository
public class TourMySQLRepository extends GeneralMySQLRepository<Tour> implements TourRepository {

    @Autowired
    public TourMySQLRepository(final HibernateSessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    public List<Tour> getToursByRequest(final TourType tourType, final Country country, final TransportType transportType, final HotelCategory hotelCategory, final TypeOfMeals typeOfMeals) throws DaoException {

        try (final Session session = getSession()) {


            final String sql = "SELECT *  FROM tour INNER JOIN hotel WHERE"
                    + " tour.tour_type = '" + tourType + "' AND"
                    + " tour.country = '" + country + "' AND"
                    + " tour.transport_type = '" + transportType + "' AND"
                    + " hotel.category = '" + hotelCategory + "' AND"
                    + " hotel.type_of_meals = '" + typeOfMeals + "'";

            final Query query = session.createNativeQuery(sql).addEntity(Tour.class);

            return (List<Tour>) query.list();
        } catch (HibernateException e) {
            //log.error

            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Tour> getToursWithLimit(final int start, final int size) throws DaoException {

        try (final Session session = getSession()) {

            final Query query = session.createQuery("FROM Tour");
            query.setFirstResult(start);
            query.setMaxResults(size);

            return (List<Tour>) query.list();
        } catch (HibernateException e) {
            //log.error

            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public int getCountTours() throws DaoException {

        try (final Session session = getSession()) {


            final String hql = "SELECT COUNT(DISTINCT T.id) FROM Tour T";
            final Query query = session.createQuery(hql);
            final long count = (long) query.uniqueResult();

            return (int) count;

        } catch (HibernateException e) {
            //log.error

            throw new DaoException(e.getMessage());
        }
    }
}
