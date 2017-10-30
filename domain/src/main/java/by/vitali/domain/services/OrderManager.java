package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.repository.OrderRepository;
import by.vitali.infrastructure.repository.OrderStatusRepository;
import org.hibernate.HibernateException;
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

    @Autowired
    public OrderManager(final TourManagement tourManagement, final OrderRepository orderRepository,
                        final OrderStatusRepository orderStatusRepository) {
        this.tourManagement = tourManagement;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public void save(final Order order) throws ServiceException {
        try {
            if (order == null) {
                throw new IllegalArgumentException("Order must not be null.");
            }
            orderRepository.save(order);
        } catch (HibernateException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final Order order) throws ServiceException {
        try {
            if (order == null) {
                throw new IllegalArgumentException("Order must not be null.");
            }
            orderRepository.update(order);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Order read(final Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Order id must not be null.");
            }
            return orderRepository.read(id, Order.class);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Long, String> getUserOrders(final User user) throws ServiceException {
        try {
            if (user == null) {
                throw new IllegalArgumentException("User must not be null.");
            }
            final List<Order> orders = orderRepository.getListUserOrders(user);
            final Map<Long, String> map = new HashMap<>();
            for (final Order order : orders) {
                final long id = order.getId();
                if (tourManagement.convertTourToString(id) == null) {
                    throw new IllegalArgumentException("Tour must not be null.");
                }
                map.put(id, tourManagement.convertTourToString(id));
            }
            return map;
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteOrder(final User user, final Tour tour) throws ServiceException {
        try {
            if (user == null || tour == null) {
                throw new IllegalArgumentException("User or tour must not be null.");
            }
            final Order order = orderRepository.getOrderByUserAndTour(user.getId(), tour.getId());
            orderRepository.delete(order);
        } catch (HibernateException e) {
            // logger.writeLog("ActionService deleteActions error:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void reserveTour(final Tour tour, final User user, final String orderStatus) throws ServiceException {
        try {
            if (tour == null || user == null || orderStatus == null) {
                throw new IllegalArgumentException("User or tour or order status must not be null.");
            }
            if (orderStatusRepository.getOrderStatusByOrderStatus(orderStatus) == null) {
                throw new IllegalArgumentException("Order status does not exist.");
            }
            final Order order = new Order();
            order.setTour(tour);
            order.setUser(user);
            order.setOrderStatus(orderStatusRepository.getOrderStatusByOrderStatus(orderStatus));
            save(order);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
