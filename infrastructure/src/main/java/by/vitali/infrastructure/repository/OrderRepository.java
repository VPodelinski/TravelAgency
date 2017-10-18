package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.User;


import java.util.List;

/**
 * Repository for Order.
 */
public interface OrderRepository extends CommonRepository<Order> {

    /**
     * This method returns user's orders.
     * @param user
     * @return
     * @throws DaoException
     */
    List<Order> getListUserOrders(User user) throws DaoException;

    /**
     * This method returns order depending on the user and tour.
     * @param idUser
     * @param idTour
     * @return Order
     * @throws DaoException
     */
    Order getOrderByUserAndTour(int idUser, int idTour) throws DaoException;

    /**
     * This method returns order depending on the order status.
     * @param idOrderStatus
     * @return
     * @throws DaoException
     */
    List<Order> getListOrdersByOrderStatus(int idOrderStatus) throws DaoException;

}
