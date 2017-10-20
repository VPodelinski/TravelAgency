package by.vitali.infrastructure.loader;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.*;
import by.vitali.infrastructure.repository.mysql.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class Load {

    public static void main(String[] args) throws DaoException {
        ApplicationContext context = new ClassPathXmlApplicationContext("infrastructure-context.xml");


        UserMySQLRepository userMySQLRepository = (UserMySQLRepository) context.getBean("userMySQLRepository");
        TourMySQLRepository tourMySQLRepository = (TourMySQLRepository) context.getBean("tourMySQLRepository");
        HotelMySQLRepository hotelMySQLRepository = (HotelMySQLRepository) context.getBean("hotelMySQLRepository");
        OrderStatusMySQLRepository orderStatusMySQLRepository = (OrderStatusMySQLRepository) context.getBean("orderStatusMySQLRepository");
        OrderMySQLRepository orderMySQLRepository = (OrderMySQLRepository) context.getBean("orderMySQLRepository");

        //*****************create User
        User user = new User();
        user.setName("Vitali");
        user.setSurname("Podelinski");
        user.setEmail("viivpo2010@mail.ru");
        user.setRole(RoleType.TRAVEL_AGENT);
        user.setPassword("789");

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

        userMySQLRepository.save(user);
        userMySQLRepository.save(user2);
        userMySQLRepository.save(user3);

        //*****************create Hotel with address
        Address address = new Address();
        address.setCity("Barselona");
        address.setStreet("1st street");
        address.setNumberBuilding("100");

        Address address2 = new Address();
        address2.setCity("Paris");
        address2.setStreet("5st street");
        address2.setNumberBuilding("21");

        Address address3 = new Address();
        address3.setCity("Praha");
        address3.setStreet("Praha streett");
        address3.setNumberBuilding("89");


        Hotel hotel = new Hotel();
        hotel.setAddress(address);
        hotel.setCategory(HotelCategory.FOUR);
        hotel.setTypeOfMeals(TypeOfMeals.OB);

        Hotel hotel2 = new Hotel();
        hotel2.setAddress(address2);
        hotel2.setCategory(HotelCategory.FIVE);
        hotel2.setTypeOfMeals(TypeOfMeals.AL);

        Hotel hotel3 = new Hotel();
        hotel3.setAddress(address3);
        hotel3.setCategory(HotelCategory.THREE);
        hotel3.setTypeOfMeals(TypeOfMeals.BB);

        hotelMySQLRepository.save(hotel);
        hotelMySQLRepository.save(hotel2);
        hotelMySQLRepository.save(hotel3);

        //******************************create orderstatus
        OrderStatus orderStatus1 = new OrderStatus();
        OrderStatus orderStatus2 = new OrderStatus();
        OrderStatus orderStatus3 = new OrderStatus();
        orderStatus1.setStatus("reserve");
        orderStatus2.setStatus("bought");
        orderStatus3.setStatus("plane");

        orderStatusMySQLRepository.save(orderStatus1);
        orderStatusMySQLRepository.save(orderStatus2);
        orderStatusMySQLRepository.save(orderStatus3);


        //*********************************create tour
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.NOVEMBER, 2);

        Tour tour = new Tour("Tour to Spain", 600, 10, Country.SPAIN, TourType.REST);
        tour.setHot(false);
        tour.setHotel(hotel);
        tour.setDepartureCity(DepartureCity.MINSK);
        tour.setTransportType(TransportType.BUS);
        // tour.setOrders(orders);
        tour.setDate(new Date(calendar.getTime().getTime()));


        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, Calendar.DECEMBER, 22);


        Tour tour2 = new Tour("Tour to France", 1000, 7, Country.FRANCE, TourType.REST);
        tour2.setHot(true);
        tour2.setHotel(hotel2);
        tour2.setDepartureCity(DepartureCity.WARSAW);
        tour2.setTransportType(TransportType.PLANE);
        //tour2.setOrders(orders);
        tour2.setDate(new Date(calendar2.getTime().getTime()));

        Calendar calendar3 = Calendar.getInstance();
        calendar.set(2017, Calendar.DECEMBER, 23);

        Tour tour3 = new Tour("Tour to Czech Republic", 500, 2, Country.CZECH_REPUBLIC, TourType.EXCURSION);
        tour3.setHot(true);
        tour3.setHotel(hotel3);
        tour3.setDepartureCity(DepartureCity.GRODNO);
        tour3.setTransportType(TransportType.BUS);
        //tour3.setOrders(orders);
        tour3.setDate(new java.sql.Date(calendar3.getTime().getTime()));


        tourMySQLRepository.save(tour);
        tourMySQLRepository.save(tour2);
        tourMySQLRepository.save(tour3);

        //*********************************create order
        //My order
        Order order = new Order();
        order.setUser(user);
        order.setTour(tour);
        order.setOrderStatus(orderStatus1);

        Order order2 = new Order();
        order2.setUser(user);
        order2.setTour(tour3);
        order2.setOrderStatus(orderStatus2);

        //other
        Order order3 = new Order();
        order3.setUser(user2);
        order3.setTour(tour2);
        order3.setOrderStatus(orderStatus3);

        Order order4 = new Order();
        order4.setUser(user3);
        order4.setTour(tour2);
        order4.setOrderStatus(orderStatus3);

        Order order5 = new Order();
        order5.setUser(user3);
        order5.setTour(tour3);
        order5.setOrderStatus(orderStatus1);


        Set<Order> orders = new HashSet<>();
        orders.add(order);
        orders.add(order2);


        orderMySQLRepository.save(order);
        orderMySQLRepository.save(order2);
        orderMySQLRepository.save(order3);
        orderMySQLRepository.save(order4);
        orderMySQLRepository.save(order5);


        // *************************test users method*******************************
        System.out.println("#################TEST USER###########################################");
        User us = userMySQLRepository.getUserByEmail("viivpo2010@mail.ru");//test ok
        System.out.println("##getUserByEmail## TEST METHOD USer " + us);

        User us2 = userMySQLRepository.getUserByEmailAndPassword("viivpo2010@mail.ru", "789");//test ok
        System.out.println("##getUserByEmailAndPassword## TEST METHOD USer " + us2);

        User us3 = userMySQLRepository.read(2, User.class);//test ok
        System.out.println("##read## TEST METHOD USER " + us3);

        user.setPassword("1111q");
        User us4 = userMySQLRepository.update(user);//test ok

        //userMySQLRepository.delete(user);//test ok
        List<User> users = userMySQLRepository.getUsersByRole(RoleType.CUSTOMER);
        for (User user1 : users) {
            System.out.println("##getUsersByRole## TEST METHOD USER " + user1);

        }

        List<User> users2 = userMySQLRepository.getAll(User.class);
        for (User user1 : users2) {
            System.out.println("##getAll## TEST METHOD USER " + user1);

        }
        System.out.println("#####################################################################################################");
        System.out.println("#################TEST HOTEL###########################################");

        List<Hotel> hotelrequest = hotelMySQLRepository.getHotelsByTypeOfMeals(TypeOfMeals.OB);
        for (Hotel hotel1 : hotelrequest) {
            System.out.println("##getHotelsByTypeOfMeals## TEST METHOD HOTEL " + hotel1);
        }

        List<Hotel> hotelrequest2 = hotelMySQLRepository.getHotelsByHotelCategory(HotelCategory.FOUR);
        for (Hotel hotel1 : hotelrequest2) {
            System.out.println("##getHotelsByHotelCategory## TEST METHOD HOTEL " + hotel1);
        }

        List<Hotel> hotelrequest3 = hotelMySQLRepository.getAll(Hotel.class);
        for (Hotel hotel1 : hotelrequest3) {
            System.out.println("##getAll## TEST METHOD HOTEL" + hotel1);
        }
        hotel3.setCategory(HotelCategory.FIVE);
        hotelMySQLRepository.update(hotel3);

        Hotel hotelRead = hotelMySQLRepository.read(3, Hotel.class);
        System.out.println("##read## TEST METHOD HOTEL " + hotelRead);
        System.out.println("#####################################################################################################");
        System.out.println("#################TEST ORDERSTATUS###########################################");

        OrderStatus orderStatus = orderStatusMySQLRepository.read(2, OrderStatus.class);
        System.out.println("##read## TEST METHOD ORDERSTATUS " + orderStatus);

        List<OrderStatus> orderStatuses = orderStatusMySQLRepository.getAll(OrderStatus.class);
        for (OrderStatus status : orderStatuses) {
            System.out.println("##getAll## TEST METHOD ORDERSTATUS " + status);
        }
        //orderStatus1.setStatus("test");
        //orderStatusMySQLRepository.update(orderStatus1);
        //orderStatusMySQLRepository.delete(orderStatus1);

        OrderStatus orderStatusRequest = orderStatusMySQLRepository.getOrderStatusByOrderStatus("plane");
        System.out.println("##getOrderStatusByOrderStatus## TEST METHOD ORDERSTATUS " + orderStatusRequest);

        System.out.println("#####################################################################################################");
        System.out.println("#################TEST TOUR###########################################");
//        tour.setPrice(100);
//        tour.setDiscount(50);
//        tourMySQLRepository.update(tour);
        //tourMySQLRepository.delete(tour);
        List<Tour> tours = tourMySQLRepository.getAll(Tour.class);
        for (Tour tour1 : tours) {
            System.out.println("##getAll## TEST METHOD TOUR " + tour1);
        }

        Tour tourRead = tourMySQLRepository.read(1, Tour.class);
        System.out.println("##read## TEST METHOD TOUR " + tourRead);
        System.out.println("##getCountTours()## TEST METHOD TOUR " + tourMySQLRepository.getCountTours());

        List<Tour> tours1 = tourMySQLRepository.getToursWithLimit(2, 1);
        for (Tour tour1 : tours1) {
            System.out.println("##getToursWithLimit## TEST METHOD TOUR " + tour1);
        }


        System.out.println("#####################################################################################################");
        System.out.println("#################TEST ORDER###########################################");


        List<Order> orderslist = orderMySQLRepository.getAll(Order.class);
        for (Order order1 : orderslist) {
            System.out.println("##getAll## TEST METHOD ORDER " + order1);

        }

        Order orderRead = orderMySQLRepository.read(2, Order.class);
        System.out.println("##read## TEST METHOD ORDER " + orderRead);

        //order.setOrderStatus(orderStatus3);
        //orderMySQLRepository.update(order);
        //orderMySQLRepository.delete(order);

        List<Order> orders2 = orderMySQLRepository.getListUserOrders(user2);
        for (Order order1 : orders2) {
            System.out.println("##getListUserOrders(user)## TEST METHOD ORDER " + order1);
        }

        Order orders3 = orderMySQLRepository.getOrderByUserAndTour(1,3);

            System.out.println("##getOrderByUserAndTour## TEST METHOD ORDER " + orders3);

    }

}
