package by.it_academy.agency.controller;

import by.it_academy.agency.beans.TourType;
import by.it_academy.agency.constants.DefaultValue;
import by.it_academy.agency.constants.MessageConstants;
import by.it_academy.agency.constants.PagePathConstants;
import by.it_academy.agency.constants.Parameters;
import by.it_academy.agency.exceptions.ServiceException;
import by.it_academy.agency.logger.logger;
import by.it_academy.agency.managers.ConfigurationManager;
import by.it_academy.agency.managers.MessageManager;
import by.it_academy.agency.services.interfaces.*;
import by.it_academy.agency.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ITourService tourService;
    @Autowired
    private ICountryService countryService;
    @Autowired
    private ITourTypeService tourTypeService;
    @Autowired
    private ITransportService transportService;
    @Autowired
    private IHotelTypeService hotelTypeService;
    @Autowired
    private IFoodComplexService foodComplexService;
    @Autowired
    private Pagination pagination;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String goToMainAdmin() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
    }

    @RequestMapping(value = "/create_tour", method = RequestMethod.POST)
    public String createTour(HttpServletRequest request) {
        String page;

        try {
            String country = request.getParameter(Parameters.COUNTRY);
            if (!country.isEmpty()) {
                int fk_country = countryService.getIdCountry(country);
                int fk_tour_type = Integer.parseInt(request.getParameter(Parameters.TOUR_TYPE));
                int fk_transport = Integer.parseInt(request.getParameter(Parameters.TRANSPORT));
                int fk_hotel_type = Integer.parseInt(request.getParameter(Parameters.HOTEL_TYPE));
                int fk_food_complex = Integer.parseInt(request.getParameter(Parameters.FOOD_COMPLEX));
                int cost = Integer.parseInt(request.getParameter(Parameters.COST));
                tourService.createTour(fk_country, fk_tour_type, fk_transport, fk_hotel_type, fk_food_complex, cost);
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.SUCCESS_OPERATION));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_CREATE_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_FIELDS));
            }
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
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

        try {
            List<TourType> typeTourList = tourTypeService.getAll();
            request.getSession().setAttribute(Parameters.TOUR_TYPE_LIST, typeTourList);

            List<by.it_academy.agency.beans.Transport> transportList = transportService.getAll();
            request.getSession().setAttribute(Parameters.TRANSPORT_LIST, transportList);

            List<by.it_academy.agency.beans.HotelType> hotelList = hotelTypeService.getAll();
            request.getSession().setAttribute(Parameters.HOTEL_TYPE_LIST, hotelList);

            List<by.it_academy.agency.beans.FoodComplex> foodComplexList = foodComplexService.getAll();
            request.getSession().setAttribute(Parameters.FOOD_COMPLEX_LIST, foodComplexList);
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
        }
        return page;
    }

    @RequestMapping(value = "/make_discount", method = RequestMethod.GET)
    public String goToSetDiscount(HttpServletRequest request) {
        String page;
        try {
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_SET_DISCOUNT_PAGE_PATH);
            request.getSession().setAttribute(Parameters.COUNT_TOURS_PER_PAGE, DefaultValue.COUNT_RECORDS_PER_PAGE);
            request.setAttribute(Parameters.TOURS_MAP, tourService.getToursMapLimit(DefaultValue.START_RECORD, DefaultValue.COUNT_RECORDS_PER_PAGE));
            request.setAttribute(Parameters.PAGINATION_MENU, pagination.getPaginationMenu(DefaultValue.START_PAGE, DefaultValue.COUNT_RECORDS_PER_PAGE));
        } catch (Exception e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }

    @RequestMapping(value = "/discount", method = RequestMethod.POST)
    public String setDiscount(HttpServletRequest request) {
        String page;
        try {
            String idTourString = request.getParameter(Parameters.DISCOUNTING_TOUR);
            String amountDiscountString = request.getParameter(Parameters.AMOUNT_DISCOUNT);
            if (null != amountDiscountString && null != idTourString) {
                int idTour = Integer.parseInt(idTourString);
                int amountDiscount = Integer.parseInt(amountDiscountString);
                tourService.makeDiscount(idTour, amountDiscount);

                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.SUCCESS_OPERATION));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_FIELDS));
            }
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }
}
