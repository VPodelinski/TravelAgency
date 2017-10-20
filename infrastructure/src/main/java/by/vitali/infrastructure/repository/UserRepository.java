package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.RoleType;
import by.vitali.infrastructure.model.User;

import java.util.List;

/**
 * UserMySQLRepository for User.
 */
public interface UserRepository extends CommonRepository<User> {

    /**
     * This method returns user with the specified email.
     * @param email
     * @return
     * @throws DaoException
     */
    User getUserByEmail(String email) throws DaoException;

    /**
     * This method returns user with the specified email and password.
     * @param email
     * @return
     * @throws DaoException
     */
    User getUserByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * This method returns users with the same role.
     * @param roleType
     * @return
     * @throws DaoException
     */
    List<User> getUsersByRole(RoleType roleType) throws DaoException;




}
