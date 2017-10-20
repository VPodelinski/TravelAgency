package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.model.Order;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.User;

import java.util.Map;

public interface OrderManagement extends GeneralManagement<Order> {

    Map<Integer, String> getUserOrders(User user) throws Exception;

    void deleteOrder(User user, int idTour) throws Exception;

    void reserveTour(Tour tour, User user, String orderStatus) throws Exception;

}
