package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.Country;
import by.vitali.infrastructure.model.DepartureCity;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.Tour;
import by.vitali.infrastructure.model.TourType;
import by.vitali.infrastructure.model.TransportType;
import by.vitali.infrastructure.model.TypeOfMeals;

import java.util.List;
import java.util.Map;

/**
 * Interface for tour manager.
 */
public interface TourManagement extends GeneralManagement<Tour> {
    /**
     * This method sets a discount.
     *
     * @param idTour
     * @param discount
     * @throws ServiceException
     */
    void makeDiscount(Long idTour, int discount) throws ServiceException;

    /**
     * This method returns a map of tour by request.
     *
     * @param tourType
     * @param country
     * @param transportType
     * @param hotelCategory
     * @param typeOfMeals
     * @return map
     * @throws ServiceException
     */
    Map<Long, Tour> getMapToursByRequest(final TourType tourType, final Country country,
                                           final TransportType transportType, final HotelCategory hotelCategory,
                                           final TypeOfMeals typeOfMeals) throws ServiceException;

    /**
     * This method returns a limited map of tour.
     *
     * @param start
     * @param size
     * @return map
     * @throws ServiceException
     */
    Map<Long, Tour> getToursMapLimit(int start, int size) throws ServiceException;

    /**
     * This method returns the count of tour.
     *
     * @return count
     * @throws ServiceException
     */
    int getCountTours() throws ServiceException;

    /**
     * This method adds tour to database.
     *
     * @param name
     * @param tourType
     * @param country
     * @param transportType
     * @param hotelId
     * @param departureCity
     * @param duration
     * @param price
     * @throws ServiceException
     */
    void createTour(String name, TourType tourType, Country country, TransportType transportType,
                    int hotelId, DepartureCity departureCity, int duration, int price) throws ServiceException;
}
