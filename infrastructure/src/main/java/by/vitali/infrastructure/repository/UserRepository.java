package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.model.RoleType;
import by.vitali.infrastructure.model.User;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * UserMySQLRepository for User.
 */
public interface UserRepository extends GeneralRepository<User> {

    /**
     * This method returns user with the specified email.
     * @param email
     * @return
     * @throws HibernateException
     */
    User getUserByEmail(String email) throws HibernateException;

    /**
     * This method returns user with the specified email and password.
     * @param email
     * @return
     * @throws HibernateException
     */
    User getUserByEmailAndPassword(String email, String password) throws HibernateException;

    /**
     * This method returns users with the same role.
     * @param roleType
     * @return
     * @throws HibernateException
     */
    List<User> getUsersByRole(RoleType roleType) throws HibernateException;

}
