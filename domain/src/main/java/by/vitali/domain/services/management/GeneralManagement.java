package by.vitali.domain.services.management;


import by.vitali.domain.services.exceptions.ServiceException;

import java.util.List;

/**
 * General interface for managers.
 *
 * @param <T>
 */
public interface GeneralManagement<T> {
    /**
     * This method saves object.
     *
     * @param type
     * @throws Exception
     */
    void save(T type) throws ServiceException;

    /**
     * This method updates object.
     *
     * @param type
     * @throws Exception
     */
    void update(T type) throws ServiceException;

    /**
     * This method returns object.
     *
     * @param id
     * @return
     * @throws Exception
     */
    T read(long id) throws ServiceException;

    /**
     * This method returns all objects.
     *
     * @return
     * @throws Exception
     */
    List<T> getAll() throws ServiceException;
}
