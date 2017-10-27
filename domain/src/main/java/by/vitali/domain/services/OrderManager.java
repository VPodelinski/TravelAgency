package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.OrderManagement;
import by.vitali.domain.services.OrderStatusManagement;
import by.vitali.domain.services.TourManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.repository.OrderRepository;
import by.vitali.infrastructure.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Order manager.
 */
@Service
@Transactional
public class OrderManager implements OrderManagement {

    private final OrderRepository orderRepository;

    private final TourManagement tourManagement;

    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusManagement orderStatusManagement;


    @Autowired
    public OrderManager(final TourManagement tourManagement, final OrderRepository orderRepository,
                        final OrderStatusRepository orderStatusRepository, OrderStatusManagement orderStatusManagement) {
        this.tourManagement = tourManagement;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusManagement = orderStatusManagement;
    }

    @Override
    public void save(final Order type) throws ServiceException {
        try {
            orderRepository.save(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final Order type) throws ServiceException {
        try {
            orderRepository.update(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Order read(final long id) throws ServiceException {
        try {
            return orderRepository.read(id, Order.class);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Order> getAll() throws ServiceException {
        try {
            return orderRepository.getAll(Order.class);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Long, String> getUserOrders(final User user) throws ServiceException {
        try {
            final List<Order> orders = orderRepository.getListUserOrders(user);
            final Map<Long, String> map = new HashMap<>();
            for (final Order order : orders) {
                final long id = order.getId();
                map.put(id, tourManagement.convertTourToString(id));
            }
            return map;
        } catch (DaoException e) {
            //logger.writeLog("ActionService getUserActions error:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteOrder(final User user, final Tour tour) throws ServiceException {
        try {
            final Order order = orderRepository.getOrderByUserAndTour(user.getId(), tour.getId());
            orderRepository.delete(order);
        } catch (DaoException e) {
            // logger.writeLog("ActionService deleteActions error:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void reserveTour(final Tour tour, final User user, final String orderStatus) throws ServiceException {
        try {
            final Order order = new Order();
            order.setTour(tour);
            order.setUser(user);
            //orderStatusManagement.createOrderStatus(orderStatus);
            order.setOrderStatus(orderStatusRepository.getOrderStatusByOrderStatus(orderStatus));
            save(order);
        } catch (DaoException e) {
            //logger.writeLog("ActionService reserveTour error:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

}
