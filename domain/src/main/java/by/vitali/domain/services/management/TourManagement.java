package by.vitali.domain.services.management;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.*;

import java.util.Date;
import java.util.Map;

/**
 *Interface for tour manager.
 */
public interface TourManagement extends GeneralManagement<Tour> {
    /**
     * This method sets a discount.
     * @param idTour
     * @param discount
     * @throws Exception
     */
    void makeDiscount(long idTour, int discount) throws Exception;

    /**
     *  This method returns a map of tour by request.
     * @param tourType
     * @param country
     * @param transportType
     * @param hotelCategory
     * @param typeOfMeals
     * @return
     * @throws Exception
     */
    Map<Long, String> getMapToursByRequest(final TourType tourType, final Country country, final TransportType transportType, final HotelCategory hotelCategory, final TypeOfMeals typeOfMeals) throws Exception;

    /**
     * This method sets the display of the tour
     * @param id
     * @return map
     * @throws Exception
     */
    String convertTourToString(long id) throws ServiceException;

    /**
     * This method returns a limited map of tour.
     * @param start
     * @param size
     * @return map
     * @throws Exception
     */
    Map<Long, String> getToursMapLimit(int start, int size) throws ServiceException;

    /**
     * This method returns the count of tour.
     * @return count
     * @throws Exception
     */
    int getCountTours() throws ServiceException;

    /**
     * This method adds tour to database.
     * @throws Exception
     */
    void createTour(String name, TourType tourType, Country country, TransportType transportType,

                    int hotel_id, DepartureCity departureCity, final Date date, int duration, int discount, int price) throws ServiceException;

}
