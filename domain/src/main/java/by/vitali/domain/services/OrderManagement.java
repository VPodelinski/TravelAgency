package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.User;

import java.util.Map;

/**
 * Interface for order manager.
 */
public interface OrderManagement extends GeneralManagement<Order> {

    /**
     * This method returns user's orders.
     *
     * @param user
     * @return
     * @throws ServiceException
     */
    Map<Long, String> getUserOrders(User user) throws ServiceException;

    /**
     * This method removes user's order.
     *
     * @param user
     * @param tour
     * @throws ServiceException
     */
    void deleteOrder(User user, Tour tour) throws ServiceException;

    /**
     * This method saves (reserves) user's order.
     *
     * @param tour
     * @param user
     * @param orderStatus
     * @throws ServiceException
     */
    void reserveTour(Tour tour, User user, String orderStatus) throws ServiceException;

}
