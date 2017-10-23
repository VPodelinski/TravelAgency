package by.vitali.domain.services.manager;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.TourManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.*;
import by.vitali.infrastructure.repository.HotelRepository;
import by.vitali.infrastructure.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tour manager.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TourManager implements TourManagement {

    private TourRepository tourRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    public TourManager(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public void save(Tour type) throws ServiceException {
        try {
            tourRepository.save(type);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Tour type) throws ServiceException {
        try {
            tourRepository.update(type);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Tour read(long id) throws ServiceException {
        try {
            return tourRepository.read(id, Tour.class);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Tour> getAll() throws ServiceException {
        try {
            return tourRepository.getAll(Tour.class);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void makeDiscount(final long idTour, final int discount) throws ServiceException {
        try {
            Tour tour = tourRepository.read(idTour, Tour.class);
            tour.setDiscount(discount);
            //or discount
            update(tour);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Long, String> getMapToursByRequest(final TourType tourType, final Country country, final TransportType transportType, final HotelCategory hotelCategory, final TypeOfMeals typeOfMeals) throws ServiceException {
        try {
            List<Tour> list = tourRepository.getToursByRequest(tourType, country, transportType, hotelCategory, typeOfMeals);

            Map<Long, String> map = new HashMap<>();

            for (Tour tour : list) {
                long id = tour.getId();
                map.put(id, convertTourToString(id));
            }
            return map;
        } catch (DaoException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public String convertTourToString(long id) throws ServiceException {
        try {
            Tour tour = tourRepository.read(id, Tour.class);
            String tourString =
                    tour.getName() + " " +
                            tour.getTourType() + " " +
                            tour.getCountry() + " " +
                            tour.getTransportType() + " " +
                            tour.getHotel().getCategory() + " " +
                            tour.getHotel().getTypeOfMeals() + " " +
                            tour.getDepartureCity() + " " +
                            tour.getDate() + " " +
                            tour.getDuration() + " " +
                            tour.getDiscount() + " " +
                            tour.getPrice();
            return tourString;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Long, String> getToursMapLimit(int start, int size) throws ServiceException {
        try {
            List<Tour> list = tourRepository.getToursWithLimit(start, size);
            Map<Long, String> map = new HashMap<>();
            for (Tour tour : list) {
                long idTour = tour.getId();
                map.put(idTour, convertTourToString(idTour));
            }
            return map;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getCountTours() throws ServiceException {
        try {
            return tourRepository.getCountTours();
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void createTour(final String name, final TourType tourType, final Country country, final TransportType transportType, final int hotel_id, final DepartureCity departureCity, final Date date, final int duration, final int discount, final int price) throws ServiceException {
        try {
            Tour tour = new Tour();
            tour.setName(name);
            tour.setTourType(tourType);
            tour.setCountry(country);
            tour.setTransportType(transportType);
            tour.setHotel(hotelRepository.read(hotel_id, Hotel.class));
            tour.setDepartureCity(departureCity);
            tour.setDate(date);
            tour.setDuration(duration);
            tour.setDiscount(0);
            tour.setPrice(price);
            save(tour);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }

    }
}
