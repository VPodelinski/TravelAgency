package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Tour;

import java.util.List;

/**
 * UserMySQLRepository for Tour.
 */
public interface TourRepository extends CommonRepository<Tour> {



    /**
     * THINK!!!!!!!!!!!!!!!!
     *
     * @return
     * @throws DaoException
     */
    List<Tour> getToursByRequest(String request) throws DaoException;

    List<Tour> getToursWithLimit(int start, int size) throws DaoException;

    int getCountTours() throws DaoException;

}
