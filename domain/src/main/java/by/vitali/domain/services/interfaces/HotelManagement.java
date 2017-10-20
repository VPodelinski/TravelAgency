package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;

import java.util.List;

public interface HotelManagement extends GeneralManagement<Hotel> {

    /**
     * This method returns hotels depending on the type of meals.
     * @param typeOfMeals
     * @return List<Hotel>
     * @throws DaoException
     */
    List<Hotel> getHotelsByTypeOfMeals(TypeOfMeals typeOfMeals) throws DaoException;

    /**
     *This method returns hotels depending on the hotel category.
     * @param hotelCategory
     * @return
     * @throws DaoException
     */
    List<Hotel> getHotelsByHotelCategory(HotelCategory hotelCategory) throws DaoException;

}
