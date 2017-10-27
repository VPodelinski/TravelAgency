package by.it_academy.agency.controller;

import by.it_academy.agency.beans.Tour;
import by.it_academy.agency.beans.TourType;
import by.it_academy.agency.beans.User;
import by.it_academy.agency.constants.MessageConstants;
import by.it_academy.agency.constants.PagePathConstants;
import by.it_academy.agency.constants.Parameters;
import by.it_academy.agency.exceptions.ServiceException;
import by.it_academy.agency.logger.logger;
import by.it_academy.agency.managers.ConfigurationManager;
import by.it_academy.agency.managers.MessageManager;
import by.it_academy.agency.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IActionService actionService;
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

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String goToMain() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.GET)
    public String goToReserveTour(HttpServletRequest request) {
        String page;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Parameters.USER);

            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_RESERVED_TOURS_PAGE_PATH);
            request.setAttribute(Parameters.TOURS_MAP, actionService.getUserActions(user));
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String goToSelectTour(HttpServletRequest request) {
        String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_SELECT_TOUR_PAGE_PATH);

        try {
            List<TourType> typeTourList = tourTypeService.getAll();
            request.setAttribute(Parameters.TOUR_TYPE_LIST, typeTourList);

            List<by.it_academy.agency.beans.Country> countryList = countryService.getAll();
            request.setAttribute(Parameters.COUNTRY_LIST, countryList);

            List<by.it_academy.agency.beans.Transport> transportList = transportService.getAll();
            request.setAttribute(Parameters.TRANSPORT_LIST, transportList);

            List<by.it_academy.agency.beans.HotelType> hotelList = hotelTypeService.getAll();
            request.setAttribute(Parameters.HOTEL_TYPE_LIST, hotelList);

            List<by.it_academy.agency.beans.FoodComplex> foodComplexList = foodComplexService.getAll();
            request.setAttribute(Parameters.FOOD_COMPLEX_LIST, foodComplexList);
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
        }
        return page;
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public String selectTour(HttpServletRequest request) {
        String page;
        int fk_tourType = Integer.parseInt(request.getParameter(Parameters.TOUR_TYPE));
        int fk_country = Integer.parseInt(request.getParameter(Parameters.COUNTRY));
        int fk_transport = Integer.parseInt(request.getParameter(Parameters.TRANSPORT));
        int fk_hotelType = Integer.parseInt(request.getParameter(Parameters.HOTEL_TYPE));
        int fk_foodComplex = Integer.parseInt(request.getParameter(Parameters.FOOD_COMPLEX));

        try {
            Map<Integer, String> map = tourService.getMapToursByRequest(fk_tourType, fk_country, fk_transport, fk_hotelType, fk_foodComplex);
            if (!map.isEmpty()) {
                request.setAttribute(Parameters.TOURS_MAP, map);
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_RESERVE_PAGE_PATH);

            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_SELECT_TOUR_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_TOUR_LIST_IS_EMPTY, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_TOURS_LIST));
            }
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String reserveTour(HttpServletRequest request) {
        String page;
        try {
            String idTourString = request.getParameter(Parameters.RESERVING_TOUR);

            if (null != idTourString) {
                int idTour = Integer.parseInt(idTourString);
                Tour tour = tourService.getById(idTour);

                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(Parameters.USER);

                String actionType = request.getParameter(Parameters.COMMAND);

                actionService.reserveTour(tour, user, actionType);

                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.RESERVE_TOUR));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_CHOICE));
            }

        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancelReservation(HttpServletRequest request) {
        String page;
        try {
            String idTourString = request.getParameter(Parameters.RESERVING_TOUR);

            if (null != idTourString) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(Parameters.USER);

                int idTour = Integer.parseInt(idTourString);
                actionService.deleteAction(user, idTour);

                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.CANCEL_RESERVING));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_CHOICE));
            }
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }
}
