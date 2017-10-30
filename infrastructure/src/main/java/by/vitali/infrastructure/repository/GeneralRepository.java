package by.vitali.infrastructure.repository;

import org.hibernate.HibernateException;

/**
 * Common  Repository interface.
 */
public interface GeneralRepository<T> {

    /**
     * This method saves object.
     *
     * @param t
     * @return T type
     * @throws HibernateException
     */
    T save(T t) throws HibernateException;

    /**
     * This method reads object.
     *
     * @param id
     * @param t
     * @return T type
     * @throws HibernateException
     */
    T read(long id, Class<T> t) throws HibernateException;

    /**
     * This method updates object.
     *
     * @param t
     * @return T type
     * @throws HibernateException
     */
    T update(T t) throws HibernateException;

    /**
     * This method removes object.
     *
     * @param t
     * @return T type
     * @throws HibernateException
     */
    T delete(T t) throws HibernateException;

}
