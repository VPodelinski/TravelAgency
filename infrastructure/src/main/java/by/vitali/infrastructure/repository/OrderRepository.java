package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.User;


import java.util.List;

/**
 * UserMySQLRepository for Order.
 */
public interface OrderRepository extends CommonRepository<Order> {
    /**
     * возвращает все заказы юсера
     * @param user
     * @return
     * @throws DaoException
     */
    List<Order> getListUserOrders(User user) throws DaoException;

    /**
     *
     * @param idUser
     * @param idTour
     * @return
     * @throws DaoException
     */
    Order getOrderByUserAndTour(int idUser, int idTour) throws DaoException;

    /**
     *
     * @param idOrderStatus
     * @return
     * @throws DaoException
     */
    List<Order> getListOrdersByOrderStatus(int idOrderStatus) throws DaoException;

}
