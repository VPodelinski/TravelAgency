package by.vitali.domain.services.management;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;

/**
 * Interface for order status manager.
 */
public interface OrderStatusManagement extends GeneralManagement<OrderStatus> {

    /**
     * This method return order type.
     *
     * @param status
     * @return order status
     * @throws DaoException
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws ServiceException;
}
