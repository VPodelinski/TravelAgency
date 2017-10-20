package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;

/**
 * Repository for OrderStatus.
 */
public interface OrderStatusRepository extends GeneralRepository<OrderStatus> {

    /**
     * This method return order type.
     * @param status
     * @return
     * @throws DaoException
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws DaoException;
}
