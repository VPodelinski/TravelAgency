package by.vitali.web.controller;


import by.vitali.domain.services.OrderStatusManagement;
import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.HotelManagement;
import by.vitali.domain.services.TourManagement;
import by.vitali.infrastructure.model.Country;
import by.vitali.infrastructure.model.DepartureCity;
import by.vitali.infrastructure.model.HotelCategory;
import by.vitali.infrastructure.model.TourType;
import by.vitali.infrastructure.model.TransportType;
import by.vitali.infrastructure.model.TypeOfMeals;
import by.vitali.web.constants.MessageConstants;
import by.vitali.web.constants.PagePathConstants;
import by.vitali.web.constants.Parameters;
import by.vitali.web.managers.ConfigurationManager;
import by.vitali.web.managers.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for guest.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    final private TourManagement tourManagement;
    final private HotelManagement hotelManagement;
    final private OrderStatusManagement orderStatusManagement;

    @Autowired
    public AdminController(final TourManagement tourManagement, final HotelManagement hotelManagement, final OrderStatusManagement orderStatusManagement) {
        this.tourManagement = tourManagement;
        this.hotelManagement = hotelManagement;
        this.orderStatusManagement = orderStatusManagement;

    }

    /**
     * Method returns main page.
     *
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String goToMainAdmin() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
    }

    /**
     * Method returns page for create hotel.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add_hotel", method = RequestMethod.POST)
    public String addHotel(final HttpServletRequest request) {
        String page;
        try {
            final String city = request.getParameter(Parameters.CITY);
            final String street = request.getParameter(Parameters.STREET);
            final String numbOfBuilding = request.getParameter(Parameters.NUMB_OF_BUILDING);
            final HotelCategory hotelCategory = HotelCategory.valueOf(request.getParameter(Parameters.HOTEL_CATEGORY));
            final TypeOfMeals typeOfMeals = TypeOfMeals.valueOf(request.getParameter(Parameters.TYPE_OF_MEAL));

            if (!city.isEmpty() & !street.isEmpty() & !numbOfBuilding.isEmpty() & null != hotelCategory & null != typeOfMeals) {
                hotelManagement.createHotel(city, street, numbOfBuilding, hotelCategory, typeOfMeals);
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.SUCCESS_OPERATION));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_FIELDS));
            }
        } catch (ServiceException e) {
            // logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        } catch (NumberFormatException e) {
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
            request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
        }
        return page;
    }

    /**
     * Method return page add hotel.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add_hotel", method = RequestMethod.GET)
    public String goToAddHotel(final HttpServletRequest request) {
        final String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_ADD_HOTEL_PAGE_PATH);

        final List<HotelCategory> hotelCategoryList = Arrays.asList(HotelCategory.values());
        request.getSession().setAttribute(Parameters.HOTEL_CATEGORY_LIST, hotelCategoryList);

        final List<TypeOfMeals> typeOfMealsList = Arrays.asList(TypeOfMeals.values());
        request.getSession().setAttribute(Parameters.TYPE_OF_MEALS_LIST, typeOfMealsList);

        return page;
    }

    @RequestMapping(value = "/add_status", method = RequestMethod.POST)
    public String addOrderStatus(final HttpServletRequest request) {
        String page;
        try {
            final String orderStatus = request.getParameter(Parameters.ORDERSTATUS);

            if (!orderStatus.isEmpty()) {
                orderStatusManagement.createOrderStatus(orderStatus);
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.SUCCESS_OPERATION));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_FIELDS));
            }
        } catch (ServiceException e) {
            // logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        } catch (NumberFormatException e) {
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
            request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
        }
        return page;
    }

    @RequestMapping(value = "/add_status", method = RequestMethod.GET)
    public String goToAddOrderStatus(final HttpServletRequest request) {
        final String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_ADD_ORDER_STATUS_PAGE_PATH);
        return page;
    }


    /**
     * Method returns page for create tour.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/create_tour", method = RequestMethod.POST)
    public String createTour(final HttpServletRequest request) {
        String page;

        try {
            final String nameTour = request.getParameter(Parameters.NAME_TOUR);
            if (!nameTour.isEmpty()) {
                final TourType tourType = TourType.valueOf(request.getParameter(Parameters.TOUR_TYPE));
                final Country chooseCountry = Country.valueOf(request.getParameter(Parameters.COUNTRY));
                final TransportType transportType = TransportType.valueOf(request.getParameter(Parameters.TRANSPORT_TYPE));
                final int hotelId = Integer.parseInt(request.getParameter(Parameters.HOTEL_ID));
                final DepartureCity departureCity = DepartureCity.valueOf(request.getParameter(Parameters.DEPARTURE_CITY));
                final int duratin = Integer.parseInt(request.getParameter(Parameters.DURATION));
                final int price = Integer.parseInt(request.getParameter(Parameters.PRICE));
                tourManagement.createTour(nameTour, tourType, chooseCountry, transportType, hotelId, departureCity, duratin, price);
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.SUCCESS_OPERATION));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_FIELDS));
            }
        } catch (ServiceException e) {
            // logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        } catch (NumberFormatException e) {
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
            request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
        }
        return page;
    }

    /**
     * Method returns page for create tour.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/create_tour", method = RequestMethod.GET)
    public String goToCreateTour(final HttpServletRequest request) {
        final String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
        final List<TourType> tourTypeList = Arrays.asList(TourType.values());
        request.getSession().setAttribute(Parameters.TOUR_TYPE_LIST, tourTypeList);
        final List<Country> countryList = Arrays.asList(Country.values());
        request.getSession().setAttribute(Parameters.COUNTRY_LIST, countryList);
        final List<TransportType> transportTypeList = Arrays.asList(TransportType.values());
        request.getSession().setAttribute(Parameters.TRANSPORT_TYPE_LIST, transportTypeList);
        final List<DepartureCity> departureCityList = Arrays.asList(DepartureCity.values());
        request.getSession().setAttribute(Parameters.DEPARTURE_CITY_LIST, departureCityList);
        return page;
    }

}
