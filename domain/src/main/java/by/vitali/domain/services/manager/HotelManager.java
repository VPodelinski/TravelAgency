package by.vitali.domain.services.manager;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.HotelManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.Address;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DaoException.class ,readOnly = false)
public class HotelManager implements HotelManagement {

    private HotelRepository hotelRepository;


    @Autowired
    public HotelManager(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getHotelsByTypeOfMeals(TypeOfMeals typeOfMeals) throws ServiceException {
        try {
            return hotelRepository.getHotelsByTypeOfMeals(typeOfMeals);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Hotel> getHotelsByHotelCategory(HotelCategory hotelCategory) throws ServiceException {
        try {
            return hotelRepository.getHotelsByHotelCategory(hotelCategory);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void save(Hotel type) throws ServiceException {
        try {
            hotelRepository.save(type);
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public void update(Hotel type) throws ServiceException {

    }

    @Override
    public Hotel read(long id) throws ServiceException {
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
    public void createHotel(String city, String street, String numbBuilding, HotelCategory category, TypeOfMeals typeOfMeals) throws ServiceException {
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setNumberBuilding(numbBuilding);
        Hotel hotel = new Hotel();
        hotel.setCategory(category);
        hotel.setTypeOfMeals(typeOfMeals);
        hotel.setAddress(address);
        save(hotel);
    }
}
