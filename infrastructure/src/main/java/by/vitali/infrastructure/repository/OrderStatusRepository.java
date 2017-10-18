package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;

/**
 * UserMySQLRepository for OrderStatus.
 */
public interface OrderStatusRepository extends CommonRepository<OrderStatus> {
    /**
     * возвращает статус позже пересмотри!!!!
     * @param status
     * @return
     * @throws Exception
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws DaoException;

}
