package by.vitali.infrastructure.repository;


import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Repository for Hotel.
 */
public interface HotelRepository extends GeneralRepository<Hotel> {

    /**
     * This method returns hotels depending on the type of meals.
     *
     * @param typeOfMeals
     * @return List<Hotel>
     * @throws HibernateException
     */
    List<Hotel> getHotelsByTypeOfMeals(TypeOfMeals typeOfMeals) throws HibernateException;

    /**
     * This method returns hotels depending on the hotel category.
     *
     * @param hotelCategory
     * @return
     * @throws HibernateException
     */
    List<Hotel> getHotelsByHotelCategory(HotelCategory hotelCategory) throws HibernateException;

    /**
     * This method returns all hotels.
     * @return
     * @throws HibernateException
     */
    List<Hotel> getHotels() throws HibernateException;
}
