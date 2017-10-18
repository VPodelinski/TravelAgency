package by.vitali.infrastructure.dao;

import by.vitali.infrastructure.dao.interfaces.IUserDAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.RoleType;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

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
