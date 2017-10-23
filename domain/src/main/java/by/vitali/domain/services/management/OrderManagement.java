package by.vitali.domain.services.management;

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
     * @throws Exception
     */
    Map<Long, String> getUserOrders(User user) throws ServiceException;

    /**
     * This method removes user's order.
     *
     * @param user
     * @param tour
     * @throws Exception
     */
    void deleteOrder(User user, Tour tour) throws ServiceException;

    /**
     * This method saves (reserves) user's order.
     *
     * @param tour
     * @param user
     * @param orderStatus
     * @throws Exception
     */
    void reserveTour(Tour tour, User user, String orderStatus) throws ServiceException;

}
