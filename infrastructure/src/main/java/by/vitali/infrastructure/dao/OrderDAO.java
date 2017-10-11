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

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO{

    @Override
    public Order find(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        Order order = null;
        try {
            String hql = "SELECT O FROM Order O WHERE O.user_id=:id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            order = (Order) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Order> orders = null;
        try {
            String hql = "FROM Order";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            orders = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return orders;
    }

    @Override
    public List<Order> getListUserOrders(User user) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Order> orders = null;
        try {
            String hql = "SELECT O FROM Order O WHERE O.user_id=:id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", user.getId());
            orders =  query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return orders;
    }

    @Override
    public Order getOrderByUserAndTour(int idUser, int idTour) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        Order order = null;
        try {
            String hql = "SELECT O FROM Order O WHERE O.user_id=:user_id and O.tour_id:=tour_id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("user_id", idUser).setParameter("tour_id", idTour);

            order = (Order) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return order;
    }

    @Override
    public List<Order> getListOrdersByOrderStatus(int idOrderStatus) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<Order> orders = null;
        try {
            String hql = "SELECT O FROM Order O WHERE O.status_id=:status_id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("status_id", idOrderStatus);
            orders =  query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return orders;
    }


}
