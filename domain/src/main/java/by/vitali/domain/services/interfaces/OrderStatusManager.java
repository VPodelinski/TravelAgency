package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.User;

import java.util.List;
import java.util.Map;

public class OrderStatusManager implements OrderManagement {
    @Override
    public Map<Integer, String> getUserOrders(User user) throws Exception {
        return null;
    }

    @Override
    public void deleteOrder(User user, int idTour) throws Exception {

    }

    @Override
    public void reserveTour(Tour tour, User user, String orderStatus) throws Exception {

    }

    @Override
    public void save(Order type) throws Exception {

    }

    @Override
    public void update(Order type) throws Exception {

    }

    @Override
    public Order read(int id) throws Exception {
        return null;
    }

    @Override
    public List<Order> getAll() throws Exception {
        return null;
    }
}
