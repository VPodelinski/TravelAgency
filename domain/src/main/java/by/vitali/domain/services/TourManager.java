package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.Country;
import by.vitali.infrastructure.model.DepartureCity;
import by.vitali.infrastructure.model.Hotel;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.TourType;
import by.vitali.infrastructure.model.TransportType;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.infrastructure.repository.HotelRepository;
import by.vitali.infrastructure.repository.TourRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tour manager.
 */
@Service
@Transactional
public class TourManager implements TourManagement {

    final private TourRepository tourRepository;

    final private HotelRepository hotelRepository;

    @Autowired
    public TourManager(final TourRepository tourRepository, final HotelRepository hotelRepository) {
        this.tourRepository = tourRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void save(final Tour tour) throws ServiceException {
        try {
            if (tour == null) {
                throw new IllegalArgumentException("Tour must not be null.");
            }
            tourRepository.save(tour);
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final Tour tour) throws ServiceException {
        try {
            if (tour == null) {
                throw new IllegalArgumentException("Tour must not be null.");
            }
            tourRepository.update(tour);
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Tour read(final Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Tour id must not be null.");
            }
            return tourRepository.read(id, Tour.class);
        } catch (HibernateException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Tour> getAll() throws ServiceException {
        try {
            return tourRepository.getAll();
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void makeDiscount(final Long idTour, final int discount) throws ServiceException {
        try {
            if (idTour == null || discount < 0) {
                throw new IllegalArgumentException("Tour id must not be null and discount must be more than 0.");
            }
            final Tour tour = tourRepository.read(idTour, Tour.class);
            tour.setDiscount(discount);
            update(tour);
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Long, String> getMapToursByRequest(final TourType tourType, final Country country, final TransportType transportType, final HotelCategory hotelCategory, final TypeOfMeals typeOfMeals) throws ServiceException {
        try {
            if (tourType == null || country == null || transportType == null || hotelCategory == null || typeOfMeals == null) {
                throw new IllegalArgumentException("Tour type, country, transport type, hotel category, type of meals must not be null.");
            }
            final List<Tour> list = tourRepository.getToursByRequest(tourType, country, transportType, hotelCategory, typeOfMeals);

            final Map<Long, String> map = new HashMap<>();

            for (final Tour tour : list) {
                final long id = tour.getId();
                map.put(id, convertTourToString(id));
            }
            return map;
        } catch (HibernateException e) {
            // logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public String convertTourToString(final Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Tour id must not be null.");

            }
            final Tour tour = tourRepository.read(id, Tour.class);
            final String tourString =
                    tour.getName()
                            + " " + tour.getTourType()
                            + " " + tour.getCountry()
                            + " " + tour.getTransportType()
                            + " " + tour.getHotel().getCategory()
                            + " " + tour.getHotel().getTypeOfMeals()
                            + " " + tour.getDepartureCity()
                            + " " + tour.getDuration()
                            + " " + tour.getDiscount()
                            + " " + tour.getPrice();
            return tourString;
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Map<Long, String> getToursMapLimit(final int start, final int size) throws ServiceException {
        try {
            if (start < 0 || size < 0) {
                throw new IllegalArgumentException("Start and size must be more 0.");

            }
            final List<Tour> list = tourRepository.getToursWithLimit(start, size);
            final Map<Long, String> map = new HashMap<>();
            for (final Tour tour : list) {
                final long idTour = tour.getId();
                map.put(idTour, convertTourToString(idTour));
            }
            return map;
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getCountTours() throws ServiceException {
        try {
            return tourRepository.getCountTours();
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void createTour(final String name, final TourType tourType, final Country country,
                           final TransportType transportType, final int hotelId,
                           final DepartureCity departureCity, final int duration,
                           final int price) throws ServiceException {
        if (name == null || tourType == null || country == null || transportType == null || departureCity == null || duration < 0 || price < 0) {
            throw new IllegalArgumentException("Name tour, type tour, country, departure city must not be null and duration, price must be more 0.");
        }
        if (hotelRepository.read(hotelId, Hotel.class) == null) {
            throw new IllegalArgumentException("Hotel does not exist.");

        }
        final Tour tour = new Tour();
        tour.setName(name);
        tour.setTourType(tourType);
        tour.setCountry(country);
        tour.setTransportType(transportType);
        tour.setHotel(hotelRepository.read(hotelId, Hotel.class));
        tour.setDepartureCity(departureCity);
        tour.setDuration(duration);
        tour.setDiscount(0);
        tour.setPrice(price);
        save(tour);
    }
}
