package by.vitali.domain.services.management;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.*;

import java.util.Date;
import java.util.Map;

/**
 *
 */
public interface TourManagement extends GeneralManagement<Tour> {
    /**
     * @param idTour
     * @param discount
     * @throws Exception
     */
    void makeDiscount(long idTour, int discount) throws Exception;

    /**
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
     * @param id
     * @return
     * @throws Exception
     */
    String convertTourToString(long id) throws ServiceException;

    /**
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    Map<Long, String> getToursMapLimit(int start, int size) throws ServiceException;

    /**
     * @return
     * @throws Exception
     */
    int getCountTours() throws ServiceException;

    /**
     * @throws Exception
     */
    void createTour(String name, TourType tourType, Country country, TransportType transportType,

                    int hotel_id, DepartureCity departureCity, final Date date, int duration, int discount, int price) throws ServiceException;

}
