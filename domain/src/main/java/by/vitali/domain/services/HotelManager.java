package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.HotelManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Address;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Hotel manager.
 */
@Service
@Transactional
public class HotelManager implements HotelManagement {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelManager(final HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getHotelsByTypeOfMeals(final TypeOfMeals typeOfMeals) throws ServiceException {
        try {
            return hotelRepository.getHotelsByTypeOfMeals(typeOfMeals);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Hotel> getHotelsByHotelCategory(final HotelCategory hotelCategory) throws ServiceException {
        try {
            return hotelRepository.getHotelsByHotelCategory(hotelCategory);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void save(final Hotel type) throws ServiceException {
        try {
            hotelRepository.save(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public void update(final Hotel type) throws ServiceException {
        try {
            hotelRepository.update(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Hotel read(final long id) throws ServiceException {
        try {
            return hotelRepository.read(id, Hotel.class);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public List<Hotel> getAll() throws ServiceException {
        try {
            return hotelRepository.getAll(Hotel.class);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void createHotel(final String city, final String street, final String numbBuilding, final HotelCategory category, final TypeOfMeals typeOfMeals) throws ServiceException {
        final Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setNumberBuilding(numbBuilding);
        final Hotel hotel = new Hotel();
        hotel.setCategory(category);
        hotel.setTypeOfMeals(typeOfMeals);
        hotel.setAddress(address);
        save(hotel);
    }
}
