package by.vitali.infrastructure.dao;

import by.vitali.infrastructure.dao.interfaces.IOrderDAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

    @Override
    public List<Order> getListUserOrders(final User user) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT O FROM Order O WHERE O.user_id=:id";
            final Query query = session.createQuery(hql);
            query.setParameter("id", user.getId());
            transaction.commit();
            return (List<Order>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Order getOrderByUserAndTour(final int idUser, final int idTour) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT O FROM Order O WHERE O.user_id=:user_id and O.tour_id:=tour_id";
            final Query query = session.createQuery(hql);
            query.setParameter("user_id", idUser).setParameter("tour_id", idTour);
            transaction.commit();
            return (Order) query.uniqueResult();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Order> getListOrdersByOrderStatus(final int idOrderStatus) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT O FROM Order O WHERE O.status_id=:status_id";
            final Query query = session.createQuery(hql);
            query.setParameter("status_id", idOrderStatus);
            transaction.commit();
            return (List<Order>) query.list();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }
}
