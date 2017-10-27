package by.vitali.domain.services.manager;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.OrderStatusManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;
import by.vitali.infrastructure.repository.OrderStatusRepository;
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
            return orderStatusRepository.getOrderStatusByOrderStatus(status);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public void save(final OrderStatus type) throws ServiceException {
        try {
            orderStatusRepository.save(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final OrderStatus type) throws ServiceException {
        try {
            orderStatusRepository.update(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public OrderStatus read(final long id) throws ServiceException {
        try {
            return orderStatusRepository.read(id, OrderStatus.class);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public List<OrderStatus> getAll() throws ServiceException {
        try {
            return orderStatusRepository.getAll(OrderStatus.class);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public void createOrderStatus(final String status) throws ServiceException {

        final OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatus(status);
        save(orderStatus);

    }
}
