package by.vitali.infrastructure.dao;

import by.vitali.infrastructure.dao.interfaces.IOrderStatusDAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public  class OrderStatusDAO extends AbstractDAO<OrderStatus> implements IOrderStatusDAO {

    @Override
    public OrderStatus find(long id) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        OrderStatus orderStatus = null;
        try {
            String hql = "SELECT OS FROM OrderStatus OS WHERE OS.id=:id";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            orderStatus = (OrderStatus) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return orderStatus;
    }

    @Override
    public List<OrderStatus> getAll() throws DaoException {
        Session session = null;
        Transaction transaction = null;
        List<OrderStatus> orderStatuses = null;
        try {
            String hql = "FROM OrderStatus";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            orderStatuses = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return orderStatuses;
    }

    @Override
    public OrderStatus getOrderStatusByOrderStatus(String status) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        OrderStatus orderStatus = null;
        try {
            String hql = "SELECT OS FROM OrderStatus OS WHERE OS.status=:status";
            session = HibernateSessionManager.getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("status", status);
            orderStatus = (OrderStatus) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            //log.error
            transaction.rollback();
            throw new DaoException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
        return orderStatus;
    }
}
