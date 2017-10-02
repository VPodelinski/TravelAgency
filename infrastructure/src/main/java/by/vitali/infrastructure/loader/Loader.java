package by.vitali.infrastructure.loader;

import by.vitali.infrastructure.module.*;
import by.vitali.infrastructure.module.enums.*;
import by.vitali.infrastructure.utils.HibernateManager;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class Loader {
    public static void main(String[] args) {

        Session session = new HibernateManager().getSession();

        session.beginTransaction();

        User user1 = new User("Vitali", "Podelinski", "viivpo2010@mail.ru", "viivpo2010", "1234", RoleType.TRAVELAGENT);
        //User user2 = new User("Ivan", "Ivanov", "ivan2010@mail.ru", "ivan2010", "4567", RoleType.USER);

        Address address1 = new Address("Barselona", "5th street", "5a");
        //Address address2 = new Address("Praga", "3th street", "90");

        Hotel hotel1 = new Hotel(address1, HotelCategory.HOTELCATEGORY5, TypeOfMeals.UAL);
        //Hotel hotel2 = new Hotel(address2, HotelCategory.HOTELCATEGORY4, TypeOfMeals.AL);

        Tour tour1 = new Tour("Tour to Barselona", 400, 40, false, 8,
                DepartureCity.MINSK, Country.COUNTRY3, TourType.REST, TransportType.PLANE, hotel1);
        //Tour tour2 = new Tour("Tour to Praga", 300, 30, false, 4,
        //DepartureCity.GRODNO, Country.COUNTRY10, TourType.REST, TransportType.BUS, hotel2);

        Order order = new Order();
        Set<Tour> tours = new HashSet<>();
        tours.add(tour1);
        hotel1.setTours(tours);

        Set<Order> orders = new HashSet<>();
        orders.add(order);

        user1.setOrders(orders);
        tour1.setOrders(orders);

        order.setTour(tour1);
        order.setUser(user1);

        session.save(user1);
        //session.save(tour1);
        // session.save(hotel1);
        session.save(order);
        //session.save(hotel2);
        //session.save(tour2);
        //Sessions
        //session.save(user2);
        session.getTransaction().commit();
        session.close();

    }
}
