package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.*;

import java.util.List;

/**
 * Repository for Tour.
 */
public interface TourRepository extends GeneralRepository<Tour> {

//*********************Think***************************

    /**
     *
     * @param tourType
     * @param country
     * @param transportType
     * @param category
     * @param typeOfMeals
     * @return
     * @throws DaoException
     */
    List<Tour> getToursByRequest(TourType tourType, Country country, TransportType transportType, HotelCategory category, TypeOfMeals typeOfMeals) throws DaoException;

    /**
     * This method returns a limited list of tours.
     * @param start
     * @param size
     * @return  List<Tour>
     * @throws DaoException
     */

    List<Tour> getToursWithLimit(int start, int size) throws DaoException;

    /**
     * This method returns count of tours.
     * @return int count
     * @throws DaoException
     */
    int getCountTours() throws DaoException;

}
