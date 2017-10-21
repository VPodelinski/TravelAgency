package by.vitali.domain.services.management;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;

/**
 *
 */
public interface OrderStatusManagement extends GeneralManagement<OrderStatus> {

    /**
     * This method return order type.
     * @param status
     * @return
     * @throws DaoException
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws ServiceException;
}
