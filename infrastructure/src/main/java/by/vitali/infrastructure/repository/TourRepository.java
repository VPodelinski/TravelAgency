package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Tour;

import java.util.List;

/**
 * Repository for Tour.
 */
public interface TourRepository extends CommonRepository<Tour> {

//*********************Think***************************
    /**
     * This method returns tours appropriate to the request.
     * @param request
     * @return List<Tour>
     * @throws DaoException
     */
    List<Tour> getToursByRequest(String request) throws DaoException;

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
