package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.OrderStatus;
import by.vitali.infrastructure.repository.OrderStatusRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Order status manager.
 */
@Service
@Transactional
public class OrderStatusManager implements OrderStatusManagement {

    final private OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusManager(final OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderStatus getOrderStatusByOrderStatus(final String status) throws ServiceException {
        try {
            if (status == null) {
                throw new IllegalArgumentException("Order status must not be null.");
            }
            return orderStatusRepository.getOrderStatusByOrderStatus(status);
        } catch (HibernateException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public void save(final OrderStatus status) throws ServiceException {
        try {
            if (status == null) {
                throw new IllegalArgumentException("Order status must not be null.");
            }
            orderStatusRepository.save(status);
        } catch (HibernateException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final OrderStatus status) throws ServiceException {
        try {
            if (status == null) {
                throw new IllegalArgumentException("Order status must not be null.");
            }
            orderStatusRepository.update(status);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public OrderStatus read(final Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Order id  must not be null.");
            }
            return orderStatusRepository.read(id, OrderStatus.class);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<OrderStatus> getAll() throws ServiceException {
        try {
            return orderStatusRepository.getOrderStatuses();
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void createOrderStatus(final String status) throws ServiceException {
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null.");
        }
        final OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatus(status);
        save(orderStatus);
    }
}
