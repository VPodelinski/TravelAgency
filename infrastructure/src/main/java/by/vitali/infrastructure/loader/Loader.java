package by.vitali.infrastructure.loader;

import by.vitali.infrastructure.repository.mysql.HotelMySQLRepository;
import by.vitali.infrastructure.repository.mysql.OrderStatusMySQLRepository;
import by.vitali.infrastructure.repository.mysql.UserMySQLRepository;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.*;

import java.util.List;


public class Loader {
    public static void main(final String... args) throws DaoException {

        UserMySQLRepository userMySQLDAO = new UserMySQLRepository();

        User user1 = new User("Vitali", "Podelinski", "viivpo2010@mail.ru");
        user1.setPassword("5678");
        user1.setRole(RoleType.CUSTOMER);

        User user2 = new User("Ivan", "Podelinski", "ivan2010@mail.ru");
        user2.setPassword("1234");
        user2.setRole(RoleType.TRAVEL_AGENT);
        User user22 = new User("Petia", "Podelinski", "petia2010@mail.ru");
        user22.setPassword("1234");
        user22.setRole(RoleType.TRAVEL_AGENT);

        userMySQLDAO.save(user1);
        userMySQLDAO.save(user2);
        userMySQLDAO.save(user22);

        //userMySQLDAO.delete(user1); // not work
        List<User> users = userMySQLDAO.getAll(User.class);
        System.out.println("22222222222222@@@@@@@@@@@@ " + users.size());

        for (User user : users) {
            System.out.println(user);
        }
        User user = userMySQLDAO.read(1, User.class);
        System.out.println("222222222222222user from find " + user);

        User user3 = userMySQLDAO.getUserByEmail("viivpo2010@mail.ru");
        System.out.println("user from email " + user3);

        List<User> users1 = userMySQLDAO.getUsersByRole(RoleType.TRAVEL_AGENT);
        for (User user4 : users1) {
            System.out.println("user from role " + user4);
        }
        System.out.println("################################################################");

        Address address1 = new Address();
        address1.setCity("Blanes");
        address1.setStreet("1st street");
        address1.setNumberBuilding("45");

        Hotel hotel1 = new Hotel(address1, HotelCategory.FOUR, TypeOfMeals.FB);

        HotelMySQLRepository hotelMySQLDAO = new HotelMySQLRepository();
        hotelMySQLDAO.save(hotel1);

        OrderStatusMySQLRepository orderStatusMySQLDAO = new OrderStatusMySQLRepository();

        OrderStatus orderStatus = new OrderStatus();
        OrderStatus orderStatus1 = new OrderStatus();
        orderStatus.setStatus("bought");
        orderStatus1.setStatus("brone");

        orderStatusMySQLDAO.save(orderStatus);
        orderStatusMySQLDAO.save(orderStatus1);
      //  orderStatusMySQLDAO.find(1);

       // System.out.println("orderstatus " + orderStatusMySQLDAO.find(2));
        System.out.println("method brone" + orderStatusMySQLDAO.getOrderStatusByOrderStatus("bought"));

    }
}
