package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;

import java.util.List;

/**
 * Common  Repository interface.
 */
public interface CommonRepository<T> {

    /**
     * This method saves object.
     * @param t
     * @return T
     * @throws DaoException
     */
    T save(T t) throws DaoException;

    /**
     * This method reads object.
     * @param id
     * @param t
     * @return T
     * @throws DaoException
     */
    T read(long id, Class<T> t) throws DaoException;

    /**
     * This method returns all objects.
     * @param type
     * @return List<T>
     * @throws DaoException
     */
    List<T> getAll(Class<T> type) throws DaoException;

    /**
     * This method updates object.
     * @param t
     * @return T
     * @throws DaoException
     */
    T update(T t) throws DaoException;

    /**
     * This method removes object.
     * @param t
     * @return T
     * @throws DaoException
     */
    T delete(T t) throws DaoException;

}
