package by.vitali.domain.services.management;


import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.User;

/**
 *
 */
public interface UserManagement extends GeneralManagement<User> {
    /**
     *
     * @param email
     * @param password
     * @return
     * @throws ServiceException
     */
    boolean authorized(String email, String password) throws ServiceException;

    /**
     *
     * @param email
     * @return
     * @throws Exception
     */
    String checkRole(String email) throws ServiceException;

    /**
     *
     * @param email
     * @return
     * @throws Exception
     */
    User getUserByEmail(String email) throws ServiceException;

    /**
     *
     * @param email
     * @return
     * @throws Exception
     */
    boolean isNewUser(String email) throws ServiceException;
}
