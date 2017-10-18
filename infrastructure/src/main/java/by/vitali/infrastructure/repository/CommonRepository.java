package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;

import java.util.List;

/**
 * UserMySQLRepository interface.
 */
public interface CommonRepository<T> {

    //create
    T save(T t) throws DaoException;

    //read
    T read(long id, Class<T> t) throws DaoException;

    List<T> getAll(Class<T> type) throws DaoException;

    //update
    T update(T t) throws DaoException;

    //delete
    T delete(T t) throws DaoException;


}
