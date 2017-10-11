package by.vitali.infrastructure.dao.interfaces;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;

/**
 * UserDAO for OrderStatus.
 */
public interface IOrderStatusDAO extends DAO<OrderStatus> {
    /**
     * возвращает статус позже пересмотри!!!!
     * @param status
     * @return
     * @throws Exception
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws DaoException;

}
