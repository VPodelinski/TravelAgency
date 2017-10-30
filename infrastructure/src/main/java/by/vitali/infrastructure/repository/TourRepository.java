package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.model.Country;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.TourType;
import by.vitali.infrastructure.model.TransportType;
import by.vitali.infrastructure.model.TypeOfMeals;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Repository for Tour.
 */
public interface TourRepository extends GeneralRepository<Tour> {

    /**
     * @param tourType
     * @param country
     * @param transportType
     * @param category
     * @param typeOfMeals
     * @return List<Tour>
     * @throws HibernateException
     */
    List<Tour> getToursByRequest(TourType tourType, Country country,
                                 TransportType transportType, HotelCategory category,
                                 TypeOfMeals typeOfMeals) throws HibernateException;

    /**
     * This method returns a limited list of tours.
     *
     * @param start
     * @param size
     * @return List<Tour>
     * @throws HibernateException
     */

    List<Tour> getToursWithLimit(int start, int size) throws HibernateException;

    /**
     * This method returns count of tours.
     *
     * @return int count
     * @throws HibernateException
     */
    int getCountTours() throws HibernateException;

    /**
     * This method returns all tours.
     *
     * @return
     * @throws HibernateException
     */
    List<Tour> getAll() throws HibernateException;

}
