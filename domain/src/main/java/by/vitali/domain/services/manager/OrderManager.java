package by.vitali.domain.services.manager;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.OrderManagement;
import by.vitali.domain.services.management.TourManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.*;
import by.vitali.infrastructure.repository.OrderRepository;
import by.vitali.infrastructure.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Order manager.
 */
@Service
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DaoException.class)
public class OrderManager implements OrderManagement {

    private OrderRepository orderRepository;

    @Autowired
    private TourManagement tourManagement;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order type) throws ServiceException {
        try {
            orderRepository.save(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Order type) throws ServiceException {

    }

    @Override
    public Order read(long id) throws ServiceException {
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
    public Map<Long, String> getUserOrders(User user) throws ServiceException {
        try {
            List<Order> orders = orderRepository.getListUserOrders(user);
            Map<Long, String> map = new HashMap<>();
            for (Order order : orders) {
                long id = order.getId();
                map.put(id, tourManagement.convertTourToString(id));
            }
            return map;
        } catch (DaoException e) {
            //logger.writeLog("ActionService getUserActions error:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteOrder(User user, Tour tour) throws ServiceException {
        try {
            Order order = orderRepository.getOrderByUserAndTour(user.getId(), tour.getId());
            orderRepository.delete(order);
        } catch (DaoException e) {
           // logger.writeLog("ActionService deleteActions error:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void reserveTour(Tour tour, User user, String orderStatus) throws ServiceException {
        try {
            Order order= new Order();
            order.setTour(tour);
            order.setUser(user);
            order.setOrderStatus(orderStatusRepository.getOrderStatusByOrderStatus(orderStatus));
            save(order);
        } catch (DaoException e) {
            //logger.writeLog("ActionService reserveTour error:" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

}
