package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;

import by.vitali.infrastructure.model.Address;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.repository.HotelRepository;
import org.hibernate.HibernateException;
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
            if (typeOfMeals == null) {
                throw new IllegalArgumentException("Type of meals must not be null.");
            }
            return hotelRepository.getHotelsByTypeOfMeals(typeOfMeals);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Hotel> getHotelsByHotelCategory(final HotelCategory hotelCategory) throws ServiceException {
        try {
            if (hotelCategory == null) {
                throw new IllegalArgumentException("Hotel category must not be null.");
            }
            return hotelRepository.getHotelsByHotelCategory(hotelCategory);
        } catch (HibernateException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void save(final Hotel hotel) throws ServiceException {
        try {
            if (hotel == null) {
                throw new IllegalArgumentException("Hotel must not be null.");
            }
            hotelRepository.save(hotel);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final Hotel hotel) throws ServiceException {
        try {
            if (hotel == null) {
                throw new IllegalArgumentException("Hotel must not be null.");
            }
            hotelRepository.update(hotel);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Hotel read(final Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Hotel id must not be null.");
            }
            return hotelRepository.read(id, Hotel.class);
        } catch (HibernateException e) {
            // logger
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    public List<Hotel> getHotels() throws ServiceException {
        try {
            return hotelRepository.getHotels();
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void createHotel(final String city, final String street, final String numbBuilding,
                            final HotelCategory category, final TypeOfMeals typeOfMeals) throws ServiceException {
        if (city.isEmpty() || street.isEmpty() || numbBuilding.isEmpty() || null == category || null == typeOfMeals) {
            throw new IllegalArgumentException("City, street, numbBuilding, hotel category, hotel type of meals must not be null.");
        }
        final Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setNumberBuilding(numbBuilding);
        final Hotel hotel = new Hotel();
        hotel.setAddress(address);
        hotel.setCategory(category);
        hotel.setTypeOfMeals(typeOfMeals);
        save(hotel);

    }
}
