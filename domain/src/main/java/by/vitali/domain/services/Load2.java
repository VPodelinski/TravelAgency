package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.manager.*;
import by.vitali.infrastructure.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Load2 {
    public static void main(String[] args) throws ServiceException {

        ApplicationContext context = new ClassPathXmlApplicationContext("domain-context.xml");


        HotelManager hotelManager = (HotelManager) context.getBean("hotelManager");
        TourManager tourManager = (TourManager) context.getBean("tourManager");
        UserManager userManager = (UserManager) context.getBean("userManager");
        OrderManager orderManager = (OrderManager) context.getBean("orderManager");
        OrderStatusManager orderStatusManager = (OrderStatusManager) context.getBean("orderStatusManager");


        hotelManager.createHotel("Pekin", "1-st street", "45", HotelCategory.FOUR, TypeOfMeals.HB);

          tourManager.createTour("Test tour", TourType.SHOPPING, Country.CHINA, TransportType.PLANE, (int) hotelManager.read(1).getId(), DepartureCity.MINSK, null, 7, 0, 400);

        //*****************create User
        User user1 = new User();
        user1.setName("Vitali");
        user1.setSurname("Podelinski");
        user1.setEmail("viivpo2010@mail.ru");
        user1.setRole(RoleType.TRAVEL_AGENT);
        user1.setPassword("789");

        User user2 = new User();
        user2.setName("Ivan");
        user2.setSurname("Ivanov");
        user2.setEmail("iviv@mail.ru");
        user2.setRole(RoleType.CUSTOMER);
        user2.setPassword("123");

        User user3 = new User();
        user3.setName("Vasia");
        user3.setSurname("Petrov");
        user3.setEmail("Petrov@gmail.com");
        user3.setRole(RoleType.CUSTOMER);
        user3.setPassword("5555");

        userManager.save(user1);
        userManager.save(user2);
        userManager.save(user3);

        System.out.println(userManager.checkRole("viivpo2010@mail.ru"));
        System.out.println(userManager.isNewUser("viqivpo2010@mail.ru"));

        //*************create ordestatus*************************
        OrderStatus orderStatus1 = new OrderStatus();
        OrderStatus orderStatus2 = new OrderStatus();
        OrderStatus orderStatus3 = new OrderStatus();
        orderStatus1.setStatus("reserve");
        orderStatus2.setStatus("bought");
        orderStatus3.setStatus("plane");

        orderStatusManager.save(orderStatus1);
        orderStatusManager.save(orderStatus2);
        orderStatusManager.save(orderStatus3);
        System.out.println( "qqqqqqqqqqqqqqqqqqqq");

//************************create order*******************
        orderManager.reserveTour(tourManager.read(1),user1,orderStatusManager.read(1).getStatus());
        orderManager.deleteOrder(user1, tourManager.read(1));
    }
}
