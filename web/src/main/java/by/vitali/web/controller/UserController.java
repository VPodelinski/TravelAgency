package by.vitali.web.controller;


import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.OrderManagement;
import by.vitali.domain.services.management.OrderStatusManagement;
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
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    final private OrderManagement orderManagement;
    final private TourManagement tourManagement;
    final private OrderStatusManagement orderStatusManagement;

    @Autowired
    public UserController(OrderManagement orderManagement, TourManagement tourManagement, OrderStatusManagement orderStatusManagement) {
        this.orderManagement = orderManagement;
        this.tourManagement = tourManagement;
        this.orderStatusManagement = orderStatusManagement;
    }


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
            request.setAttribute(Parameters.TOURS_MAP, orderManagement.getUserOrders(user));
        } catch (ServiceException e) {
            //logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String goToSelectTour(HttpServletRequest request) {
        String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_SELECT_TOUR_PAGE_PATH);

        List<TourType> tourTypeList = Arrays.asList(TourType.values());
        request.getSession().setAttribute(Parameters.TOUR_TYPE_LIST, tourTypeList);

        List<Country> countryList = Arrays.asList(Country.values());
        request.getSession().setAttribute(Parameters.COUNTRY_LIST, countryList);

        List<TransportType> transportTypeList = Arrays.asList(TransportType.values());
        request.getSession().setAttribute(Parameters.TRANSPORT_TYPE_LIST, transportTypeList);

        List<HotelCategory> hotelCategoryList = Arrays.asList(HotelCategory.values());
        request.getSession().setAttribute(Parameters.HOTEL_CATEGORY_LIST, hotelCategoryList);

        List<TypeOfMeals> typeOfMealsList = Arrays.asList(TypeOfMeals.values());
        request.getSession().setAttribute(Parameters.TYPE_OF_MEALS_LIST, typeOfMealsList);

        return page;
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public String selectTour(HttpServletRequest request) {
        String page = "";

        TourType tourType = TourType.valueOf(request.getParameter(Parameters.TOUR_TYPE));
        Country chooseCountry = Country.valueOf(request.getParameter(Parameters.COUNTRY));
        TransportType transportType = TransportType.valueOf(request.getParameter(Parameters.TRANSPORT_TYPE));
        HotelCategory hotelCategory = HotelCategory.valueOf(request.getParameter(Parameters.HOTEL_CATEGORY));
        TypeOfMeals typeOfMeals = TypeOfMeals.valueOf(request.getParameter(Parameters.TYPE_OF_MEAL));

        if (null != tourType & null != chooseCountry & null != transportType & null != hotelCategory & null != typeOfMeals) {
            try {
                Map<Long, String> map = tourManagement.getMapToursByRequest(tourType, chooseCountry, transportType, hotelCategory, typeOfMeals);
                if (!map.isEmpty()) {
                    request.setAttribute(Parameters.TOURS_MAP, map);
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_RESERVE_PAGE_PATH);

                } else {
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_SELECT_TOUR_PAGE_PATH);
                    request.setAttribute(Parameters.ERROR_TOUR_LIST_IS_EMPTY, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_TOURS_LIST));
                }
            } catch (ServiceException e) {
                //logger.writeLog(e.getMessage());
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
            }

        }  return page;
    }
    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String reserveTour(HttpServletRequest request) {
        String page;
        try {
            String idTourString = request.getParameter(Parameters.RESERVING_TOUR);
            if (null != idTourString) {
                int idTour = Integer.parseInt(idTourString);
                Tour tour = tourManagement.read(idTour);
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(Parameters.USER);
                orderManagement.reserveTour(tour, user, "RESERVE");
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.RESERVE_TOUR));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_CHOICE));
            }
        } catch (ServiceException e) {
            // logger.writeLog(e.getMessage());
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
                Long idTour = Long.parseLong(idTourString);
                Tour tour = tourManagement.read(idTour);
                orderManagement.deleteOrder(user, tour);
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.CANCEL_RESERVING));
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_CHOICE));
            }
        } catch (ServiceException e) {
            // logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }
}
