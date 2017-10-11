package by.vitali.infrastructure.dao.interfaces;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Tour;

import java.util.List;

/**
 * UserDAO for Tour.
 */
public interface ITourDAO extends DAO<Tour> {



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
