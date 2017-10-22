package by.vitali.domain.services.manager;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.OrderStatusManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.OrderStatus;
import by.vitali.infrastructure.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Order status manager.
 */
@Service
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DaoException.class)
public class OrderStatusManager implements OrderStatusManagement {

    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusManager(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public OrderStatus getOrderStatusByOrderStatus(String status) throws ServiceException {
        try {
            return orderStatusRepository.getOrderStatusByOrderStatus(status);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public void save(OrderStatus type) throws ServiceException {
        try {
            orderStatusRepository.save(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }

    }

    @Override
    public void update(OrderStatus type) throws ServiceException {

    }

    @Override
    public OrderStatus read(long id) throws ServiceException {
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
}
