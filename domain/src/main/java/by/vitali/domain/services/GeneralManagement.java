package by.vitali.domain.services;


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
     * @throws ServiceException
     */
    void save(T type) throws ServiceException;

    /**
     * This method updates object.
     *
     * @param type
     * @throws ServiceException
     */
    void update(T type) throws ServiceException;

    /**
     * This method returns object.
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    T read(Long id) throws ServiceException;

}
