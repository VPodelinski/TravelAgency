package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.repository.OrderStatusRepository;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public  class OrderStatusMySQLRepository extends CommonMySQLRepository<OrderStatus> implements OrderStatusRepository {

    @Override
    public OrderStatus getOrderStatusByOrderStatus(final String status) throws DaoException {
        Transaction transaction = null;
        try (final Session session = HibernateSessionManager.getSession().openSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT OS FROM OrderStatus OS WHERE OS.status=:status";
            final Query query = session.createQuery(hql);
            query.setParameter("status", status);
            transaction.commit();
            return (OrderStatus) query.uniqueResult();
        } catch (HibernateException e) {
            //log.error
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(e.getMessage());
        }
    }
}
