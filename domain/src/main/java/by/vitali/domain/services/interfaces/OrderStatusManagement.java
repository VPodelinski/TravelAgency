package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;

public interface OrderStatusManagement extends GeneralManagement<OrderStatus> {

    /**
     * This method return order type.
     * @param status
     * @return
     * @throws DaoException
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws Exception;
}
