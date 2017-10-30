package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.OrderStatus;

import java.util.List;

/**
 * Interface for order status manager.
 */
public interface OrderStatusManagement extends GeneralManagement<OrderStatus> {

    /**
     * This method return order type.
     *
     * @param status
     * @return order status
     * @throws ServiceException
     */
    OrderStatus getOrderStatusByOrderStatus(String status) throws ServiceException;

    /**
     * Create order status.
     *
     * @throws ServiceException
     */
    void createOrderStatus(String status) throws ServiceException;

    /**
     * This method returns order statuses.
     *
     * @return
     * @throws ServiceException
     */
    public List<OrderStatus> getAll() throws ServiceException;


}
