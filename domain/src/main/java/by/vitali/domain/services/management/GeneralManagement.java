package by.vitali.domain.services.management;



import by.vitali.domain.services.exceptions.ServiceException;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface GeneralManagement<T> {
    /**
     *
     * @param type
     * @throws Exception
     */
    void save(T type) throws ServiceException;

    /**
     *
     * @param type
     * @throws Exception
     */
    void update(T type) throws ServiceException;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    T read(long id) throws ServiceException;

    /**
     *
     * @return
     * @throws Exception
     */
    List<T> getAll() throws ServiceException;
}
