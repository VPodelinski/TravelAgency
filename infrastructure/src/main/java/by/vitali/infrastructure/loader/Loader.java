package by.vitali.infrastructure.loader;

import by.vitali.infrastructure.dao.HotelDAO;
import by.vitali.infrastructure.dao.OrderStatusDAO;
import by.vitali.infrastructure.dao.UserDAO;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.*;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.Session;

import java.util.List;
import java.util.Locale;


public class Loader {
    public static void main(final String... args) throws DaoException {

        UserDAO userDAO = new UserDAO();

        User user1 = new User("Vitali", "Podelinski", "viivpo2010@mail.ru");
        user1.setPassword("5678");
        user1.setRoles(RoleType.CUSTOMER);

        User user2 = new User("Ivan", "Podelinski", "ivan2010@mail.ru");
        user2.setPassword("1234");
        user2.setRoles(RoleType.TRAVEL_AGENT);
        User user22 = new User("Petia", "Podelinski", "petia2010@mail.ru");
        user22.setPassword("1234");
        user22.setRoles(RoleType.TRAVEL_AGENT);

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user22);

        //userDAO.delete(user1); // not work
        List<User> users = userDAO.getAll();
        System.out.println("@@@@@@@@@@@@ " + users.size());

        for (User user : users) {
            System.out.println(user);
        }
        User user = userDAO.find(2);
        System.out.println("user from find " + user);

        User user3 = userDAO.getUserByEmail("viivpo2010@mail.ru");
        System.out.println("user from email " + user3);

        List<User> users1 = userDAO.getUsersByRole(RoleType.TRAVEL_AGENT);
        for (User user4 : users1) {
            System.out.println("user from role " + user4);
        }
        System.out.println("################################################################");

        Address address1 = new Address();
        address1.setCity("Blanes");
        address1.setStreet("1st street");
        address1.setNumberBuilding("45");

        Hotel hotel1 = new Hotel(address1, HotelCategory.FOUR, TypeOfMeals.FB);

        HotelDAO hotelDAO = new HotelDAO();
        hotelDAO.save(hotel1);

        OrderStatusDAO orderStatusDAO = new OrderStatusDAO();

        OrderStatus orderStatus = new OrderStatus();
        OrderStatus orderStatus1 = new OrderStatus();
        orderStatus.setType("bought");
        orderStatus1.setType("brone");

        orderStatusDAO.save(orderStatus);
        orderStatusDAO.save(orderStatus1);
        orderStatusDAO.find(1);

        System.out.println("orderstatus " + orderStatusDAO.find(2));
        System.out.println("method brone" + orderStatusDAO.getOrderStatusByOrderStatus("bought"));

    }
}
