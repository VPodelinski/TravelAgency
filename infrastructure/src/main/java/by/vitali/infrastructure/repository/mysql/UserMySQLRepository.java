package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.repository.UserRepository;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.RoleType;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

/**
 * The implementation repository for a user.
 */
public class UserMySQLRepository extends CommonMySQLRepository<User> implements UserRepository {

    /**
     *
     * @param email
     * @return
     * @throws DaoException
     */
    @Override
    public User getUserByEmail(final String email) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT U FROM User U WHERE U.email=:email";
            final Query query = session.createQuery(hql);
            query.setParameter("email", email);
            transaction.commit();
            return (User) query.uniqueResult();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    /**
     *
     * @param roleType
     * @return
     * @throws DaoException
     */
    @Override
    public List<User> getUsersByRole(final RoleType roleType) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT U FROM User U WHERE U.role=:role";
            final Query query = session.createQuery(hql);
            query.setParameter("role", roleType);
            transaction.commit();
            return (List<User>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }
}
