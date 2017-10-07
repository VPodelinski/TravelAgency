package by.vitali.infrastructure.loader;

import by.vitali.infrastructure.model.Address;
import by.vitali.infrastructure.model.Country;
import by.vitali.infrastructure.model.DepartureCity;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.RoleType;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.TourType;
import by.vitali.infrastructure.model.TransportType;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.utils.HibernateSessionManager;
import org.hibernate.Session;



public class Loader {
    public static void main(final String... args) {

        System.setProperty("hibernate.dialect.storage_engine", "innodb");

        Session session = new HibernateSessionManager().getSession();

        session.beginTransaction();

        User user1 = new User("Vitali", "Podelinski", "viivpo2010@mail.ru");
        user1.setPassword("5678");
        user1.setRoles(RoleType.CUSTOMER);

        User user2 = new User("Ivan", "Podelinski", "ivan2010@mail.ru");
        user2.setPassword("1234");
        user2.setRoles(RoleType.TRAVEL_AGENT);

        Address address1 = new Address("Barselona", "5th street", "5a");
        //Address address2 = new Address("Praga", "3th street", "90");

        Hotel hotel1 = new Hotel(address1, HotelCategory.FIVE, TypeOfMeals.UAL);
        //Hotel hotel2 = new Hotel(address2, HotelCategory.HOTELCATEGORY4, TypeOfMeals.AL);

        Tour tour1 = new Tour("Tour to Barselona", 400, 8, Country.SPAIN, TourType.REST);
        tour1.setDiscount(40);
        tour1.setDepartureCity(DepartureCity.MINSK);
        tour1.setTransportType(TransportType.PLANE);
        tour1.setHotel(hotel1);
        //Tour tour2 = new Tour("Tour to Praga", 300, 30, false, 4,
        //DepartureCity.GRODNO, Country.COUNTRY10, TourType.REST, TransportType.BUS, hotel2);
//        OrderStatus action = new OrderStatus();
//        action.setActionType("bron");
//        Order order = new Order();
//        Set<Tour> tours = new HashSet<>();
//        tours.add(tour1);
//        hotel1.setTours(tours);
//
//        Set<Order> orders = new HashSet<>();
//        orders.add(order);
//        action.setActions(orders);
//        user1.setOrders(orders);
//        tour1.setOrders(orders);
//
//        order.setTour(tour1);
//        order.setUser(user1);

        session.save(user1);
        session.save(user2);
        //session.save(hotel1);
        //session.save(order);
        //session.save(hotel2);
        //session.save(tour2);

        //session.save(user2);
        session.getTransaction().commit();
        session.close();
    }
}
