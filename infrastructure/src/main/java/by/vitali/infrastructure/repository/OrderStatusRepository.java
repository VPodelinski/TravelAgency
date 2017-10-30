package by.vitali.infrastructure.repository;

import by.vitali.infrastructure.model.OrderStatus;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Repository for OrderStatus.
 */
public interface OrderStatusRepository extends GeneralRepository<OrderStatus> {

    /**
     * This method returns order type.
     * @param status
     * @return order status
     * @throws HibernateException
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws HibernateException;

    /**
     * This method returns all statuses.
     * @return
     * @throws HibernateException
     */
    List<OrderStatus> getOrderStatuses() throws HibernateException;
}
