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
     *
     * @param email
     * @return
     * @throws DaoException
     */
    User getUserByEmail(String email) throws DaoException;

    /**
     *
     * @param roleType
     * @return
     * @throws DaoException
     */
    List<User> getUsersByRole(RoleType roleType) throws DaoException;

}
