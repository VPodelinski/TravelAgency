package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Country;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.TourType;
import by.vitali.infrastructure.model.TransportType;
import by.vitali.infrastructure.model.TypeOfMeals;


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
     * @return
     * @throws DaoException
     */
    List<Tour> getToursByRequest(TourType tourType, Country country,
                                 TransportType transportType, HotelCategory category,
                                 TypeOfMeals typeOfMeals) throws DaoException;

    /**
     * This method returns a limited list of tours.
     *
     * @param start
     * @param size
     * @return List<Tour>
     * @throws DaoException
     */

    List<Tour> getToursWithLimit(int start, int size) throws DaoException;

    /**
     * This method returns count of tours.
     *
     * @return int count
     * @throws DaoException
     */
    int getCountTours() throws DaoException;

}
