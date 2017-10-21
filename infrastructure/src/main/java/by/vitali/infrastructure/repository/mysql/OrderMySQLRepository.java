package by.vitali.infrastructure.repository.mysql;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.repository.OrderRepository;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The implementation repository for a order.
 */
@Repository
public class OrderMySQLRepository extends GeneralMySQLRepository<Order> implements OrderRepository {

    @Autowired
    public OrderMySQLRepository(final HibernateSessionManager sessionManager) {
        super(sessionManager);
    }


    @Override
    public List<Order> getListUserOrders(final User user) throws DaoException {
        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT O FROM Order O WHERE user_id=:id";
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
    public Order getOrderByUserAndTour(final long idUser, final long idTour) throws DaoException {
        Transaction transaction = null;
        try (final Session session = getSession()) {
            transaction = session.beginTransaction();
            final String hql = "SELECT O FROM Order O WHERE user_id=:user_id AND tour_id=:tour_id";
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
}
