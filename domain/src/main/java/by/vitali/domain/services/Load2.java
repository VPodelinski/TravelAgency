package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.manager.*;
import by.vitali.infrastructure.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Load2 {
    public static void main(String[] args) throws ServiceException {

        ApplicationContext context = new ClassPathXmlApplicationContext("domain-context.xml");


        HotelManager hotelManager = (HotelManager) context.getBean("hotelManager");
        TourManager tourManager = (TourManager) context.getBean("tourManager");
        UserManager userManager = (UserManager) context.getBean("userManager");
        OrderManager orderManager = (OrderManager) context.getBean("orderManager");
        OrderStatusManager orderStatusManager = (OrderStatusManager) context.getBean("orderStatusManager");

// create hotel
        hotelManager.createHotel("Pekin", "1-st street", "45", HotelCategory.FOUR, TypeOfMeals.HB);
        hotelManager.createHotel("Barselona", "some street", "110", HotelCategory.FIVE, TypeOfMeals.UAL);

        tourManager.createTour("Test tour", TourType.SHOPPING, Country.CHINA, TransportType.PLANE, (int) hotelManager.read(1).getId(), DepartureCity.MINSK, null, 7, 0, 400);
        tourManager.createTour("Test tour 2 ", TourType.REST, Country.CZECH_REPUBLIC, TransportType.SHIP, (int) hotelManager.read(2).getId(), DepartureCity.VILNIUS, null, 10, 0, 300);

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


//************************create order*******************
        orderManager.reserveTour(tourManager.read(2), user2, orderStatusManager.read(3).getStatus());
        // orderManager.deleteOrder(user1, tourManager.read(1));

        Map<Long, String> map = orderManager.getUserOrders(user1);
        System.out.println(map.size());

        for (Map.Entry<Long, String> pair : map.entrySet()) {
            Long key = pair.getKey();                      //ключ
            String value = pair.getValue();                  //значение
            System.out.println("111111111111111111" + key + ":" + value);
        }

        //test all methods

//**********************************test user manager********************************
        User usertest = userManager.read(1);
        System.out.println("######### read user " + usertest);

        List<User> userList = userManager.getAll();
        for (User user : userList) {
            System.out.println("######### getAll user " + user);
        }

        System.out.println("######### checkrole user " + userManager.checkRole("iviv@mail.ru"));
        System.out.println("######### is new " + userManager.isNewUser("viivpo2010@mail.ru"));
        System.out.println("######### is authorized " + userManager.authorized("viivpo2010@mail.ru", "789"));
        System.out.println("######### getUser by ID " + userManager.getUserByEmail("iviv@mail.ru"));
        System.out.println("\n################################################################################3\n");

//*****************************test order status*************************************************
        System.out.println("######### read user " + orderStatusManager.read(1));
        List<OrderStatus> orderStatuses = orderStatusManager.getAll();

        for (OrderStatus orderStatus : orderStatuses) {
            System.out.println("######### getAll " + orderStatus);
        }
        System.out.println("#########get by status " + orderStatusManager.getOrderStatusByOrderStatus("reserve"));

//*****************************test hotel*************************************************
        Hotel hotel = hotelManager.read(2);
        System.out.println("#########read hotel " + hotel);

        List<Hotel> hotels = hotelManager.getAll();

        for (Hotel hotel1 : hotels) {
            System.out.println("#########getAll hotel " + hotel1);
        }

        List<Hotel> hotels2 = hotelManager.getHotelsByHotelCategory(HotelCategory.FIVE);

        for (Hotel hotel1 : hotels2) {
            System.out.println("#########by category hotel " + hotel1);
        }

        List<Hotel> hotels3 = hotelManager.getHotelsByTypeOfMeals(TypeOfMeals.UAL);

        for (Hotel hotel1 : hotels3) {
            System.out.println("#########by meals hotel " + hotel1);
        }

//*****************************test tour*************************************************
        Tour tour = tourManager.read(1);
        tour.setPrice(1000);
        tourManager.update(tour);

        List<Tour> tours = tourManager.getAll();
        for (Tour tour1 : tours) {
            System.out.println("#########getAll tourl " + tour1);
        }


        System.out.println(tourManager.getCountTours());

        Map<Long, String> map1 = tourManager.getToursMapLimit(0, 2);
        for (Map.Entry<Long, String> pair : map1.entrySet()) {
            Long key = pair.getKey();                      //ключ
            String value = pair.getValue();                  //значение
            System.out.println(key + ":" + value);
        }
        tourManager.makeDiscount(1,50);

        Map<Long, String> tours1 = tourManager.getMapToursByRequest(TourType.REST,Country.CZECH_REPUBLIC,TransportType.SHIP,HotelCategory.FIVE,TypeOfMeals.UAL
        );
        for (Map.Entry<Long, String> pair : tours1.entrySet()) {
            Long key = pair.getKey();                      //ключ
            String value = pair.getValue();                  //значение
            System.out.println(key + ":" + value);
        }

        Order order =orderManager.read(1);

        System.out.println("#########read order " + order);

        List<Order> orders = orderManager.getAll();
        for (Order order1 : orders) {
            System.out.println("#########getAll orders " + order1);
        }
    }




}
