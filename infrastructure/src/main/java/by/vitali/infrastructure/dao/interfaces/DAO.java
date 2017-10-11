package by.vitali.infrastructure.dao.interfaces;

import by.vitali.infrastructure.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * UserDAO interface.
 */
public interface DAO<T> {

    //create
    T save(T t) throws DaoException;

    //read
    T find(long id) throws DaoException;

    List<T> getAll() throws DaoException;

    //update
    T update(T t) throws DaoException;

    //delete
    T delete(T t) throws DaoException;
}
