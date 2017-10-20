package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;

import java.util.List;

public class HotelManager implements HotelManagement{

    @Override
    public List<Hotel> getHotelsByTypeOfMeals(TypeOfMeals typeOfMeals) throws DaoException {
        return null;
    }

    @Override
    public List<Hotel> getHotelsByHotelCategory(HotelCategory hotelCategory) throws DaoException {
        return null;
    }

    @Override
    public void save(Hotel type) throws Exception {

    }

    @Override
    public void update(Hotel type) throws Exception {

    }

    @Override
    public Hotel read(int id) throws Exception {
        return null;
    }

    @Override
    public List<Hotel> getAll() throws Exception {
        return null;
    }
}
