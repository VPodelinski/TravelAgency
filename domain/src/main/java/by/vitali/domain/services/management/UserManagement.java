package by.vitali.domain.services.management;


import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.User;

/**
 * Interface for user manager.
 */
public interface UserManagement extends GeneralManagement<User> {

    /**
     * This the method authorizes user.
     * @param email
     * @param password
     * @return boolean
     * @throws ServiceException
     */
    boolean authorized(String email, String password) throws ServiceException;

    /**
     * This method check user's role type.
     * @param email
     * @return role
     * @throws Exception
     */
    String checkRole(String email) throws ServiceException;

    /**
     * This method returns user by email.
     * @param email
     * @return user
     * @throws Exception
     */
    User getUserByEmail(String email) throws ServiceException;

    /**
     * This method checks is the user new.
     * @param email
     * @return boolean
     * @throws Exception
     */
    boolean isNewUser(String email) throws ServiceException;
}
