package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;
import by.vitali.infrastructure.repository.OrderStatusRepository;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The implementation repository for a order status.
 */
@Repository
public class OrderStatusMySQLRepository extends GeneralMySQLRepository<OrderStatus> implements OrderStatusRepository {

    @Autowired
    public OrderStatusMySQLRepository(final HibernateSessionManager sessionManager) {
        super(sessionManager);
    }

    @Override
    public OrderStatus getOrderStatusByOrderStatus(final String status) throws DaoException {

        try (final Session session = getSession()) {

            final String hql = "SELECT OS FROM OrderStatus OS WHERE OS.status=:status";
            final Query query = session.createQuery(hql);
            query.setParameter("status", status);

            return (OrderStatus) query.uniqueResult();
        } catch (HibernateException e) {
            //log.error

            throw new DaoException(e.getMessage());
        }
    }
}
