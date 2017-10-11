package by.vitali.infrastructure.dao.interfaces;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;

import java.util.List;

/**
 * UserDAO from Hotel
 */
public interface IHotelDAO extends DAO<Hotel> {

    /**
     *
     * @param typeOfMeals
     * @return
     * @throws DaoException
     */
    List<Hotel> getHotelsByTypeOfMeals(TypeOfMeals typeOfMeals) throws DaoException;

    /**
     *
     * @param hotelCategory
     * @return
     * @throws DaoException
     */
    List<Hotel> getHotelsByHotelCategory(HotelCategory hotelCategory) throws DaoException;

}
