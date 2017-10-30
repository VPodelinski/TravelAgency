package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.User;
import org.hibernate.HibernateException;


import java.util.List;

/**
 * Repository for Order.
 */
public interface OrderRepository extends GeneralRepository<Order> {

    /**
     * This method returns user's orders.
     * @param user
     * @return orders
     * @throws HibernateException
     */
    List<Order> getListUserOrders(User user) throws HibernateException;

    /**
     * This method returns order depending on the user and tour.
     * @param idUser
     * @param idTour
     * @return Order
     * @throws HibernateException
     */
    Order getOrderByUserAndTour(long idUser, long idTour) throws HibernateException;

}
