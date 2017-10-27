package by.vitali.domain.services.management;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;

import java.util.List;

/**
 * Interface for hotel manager.
 */
public interface HotelManagement extends GeneralManagement<Hotel> {

    /**
     * This method returns hotels depending on the type of meals.
     *
     * @param typeOfMeals
     * @return List<Hotel>
     * @throws ServiceException
     */
    List<Hotel> getHotelsByTypeOfMeals(TypeOfMeals typeOfMeals) throws ServiceException;

    /**
     * This method returns hotels depending on the hotel category.
     *
     * @param hotelCategory
     * @return
     * @throws ServiceException
     */
    List<Hotel> getHotelsByHotelCategory(HotelCategory hotelCategory) throws ServiceException;

    /**
     * This method creates hotel object.
     *
     * @throws ServiceException
     */
    void createHotel(String city, String street, String numbBuilding, HotelCategory category, TypeOfMeals typeOfMeals) throws ServiceException;

}
