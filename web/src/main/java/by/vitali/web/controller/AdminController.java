package by.vitali.web.controller;


import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.HotelManagement;
import by.vitali.domain.services.management.TourManagement;
import by.vitali.infrastructure.model.*;
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


@Controller
@RequestMapping("/admin")
public class AdminController {

    final private TourManagement tourManagement;
    final private HotelManagement hotelManagement;


    @Autowired
    public AdminController(TourManagement tourManagement, HotelManagement hotelManagement) {
        this.tourManagement = tourManagement;
        this.hotelManagement = hotelManagement;

    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String goToMainAdmin() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
    }

    @RequestMapping(value = "/add_hotel", method = RequestMethod.POST)
    public String addHotel(HttpServletRequest request) {
        String page;
        try {
            String city = request.getParameter(Parameters.CITY);
            String street = request.getParameter(Parameters.STREET);
            String numbOfBuilding = request.getParameter(Parameters.NUMB_OF_BUILDING);
            HotelCategory hotelCategory = HotelCategory.valueOf(request.getParameter(Parameters.HOTEL_CATEGORY));
            TypeOfMeals typeOfMeals = TypeOfMeals.valueOf(request.getParameter(Parameters.TYPE_OF_MEAL));

            if (!city.isEmpty() & !street.isEmpty() & !numbOfBuilding.isEmpty() & null != hotelCategory & null != typeOfMeals) {
                hotelManagement.createHotel(city, street, numbOfBuilding, hotelCategory, typeOfMeals);
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.SUCCESS_OPERATION));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_FIELDS));
            }
        } catch (
                ServiceException e)

        {
            // logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        } catch (
                NumberFormatException e)

        {
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
            request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
        }
        return page;
    }
    @RequestMapping(value = "/add_hotel", method = RequestMethod.GET)
    public String goToAddHotel(HttpServletRequest request) {
        String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_ADD_HOTEL_PAGE_PATH);

        List<HotelCategory> hotelCategoryList = Arrays.asList(HotelCategory.values());
        request.getSession().setAttribute(Parameters.HOTEL_CATEGORY_LIST, hotelCategoryList);

        List<TypeOfMeals> typeOfMealsList = Arrays.asList(TypeOfMeals.values());
        request.getSession().setAttribute(Parameters.TYPE_OF_MEALS_LIST, typeOfMealsList);

        return page;
    }

    @RequestMapping(value = "/create_tour", method = RequestMethod.POST)
    public String createTour(HttpServletRequest request) {
        String page;

        try {
            String nameTour = request.getParameter(Parameters.NAME_TOUR);
            if (!nameTour.isEmpty()) {
                TourType tourType = TourType.valueOf(request.getParameter(Parameters.TOUR_TYPE));
                Country chooseCountry = Country.valueOf(request.getParameter(Parameters.COUNTRY));
                TransportType transportType = TransportType.valueOf(request.getParameter(Parameters.TRANSPORT_TYPE));
                int hotelId = Integer.parseInt(request.getParameter(Parameters.HOTEL_ID));
                DepartureCity departureCity = DepartureCity.valueOf(request.getParameter(Parameters.DEPARTURE_CITY));
                int duratin = Integer.parseInt(request.getParameter(Parameters.DURATION));
                int price = Integer.parseInt(request.getParameter(Parameters.PRICE));
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

    @RequestMapping(value = "/create_tour", method = RequestMethod.GET)
    public String goToCreateTour(HttpServletRequest request) {
        String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);


        List<TourType> tourTypeList = Arrays.asList(TourType.values());
        request.getSession().setAttribute(Parameters.TOUR_TYPE_LIST, tourTypeList);

        List<Country> countryList = Arrays.asList(Country.values());
        request.getSession().setAttribute(Parameters.COUNTRY_LIST, countryList);

        List<TransportType> transportTypeList = Arrays.asList(TransportType.values());
        request.getSession().setAttribute(Parameters.TRANSPORT_TYPE_LIST, transportTypeList);

        List<DepartureCity> departureCityList = Arrays.asList(DepartureCity.values());
        request.getSession().setAttribute(Parameters.DEPARTURE_CITY_LIST, departureCityList);

        return page;
    }

}
