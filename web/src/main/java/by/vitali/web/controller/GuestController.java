package by.vitali.web.controller;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.UserManagement;
import by.vitali.infrastructure.model.RoleType;
import by.vitali.infrastructure.model.User;
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

/**
 * Guest controller.
 */
@Controller
public class GuestController {

    final private UserManagement userManagement;

    @Autowired
    public GuestController(final UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String startPage() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String goToRegistration() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.REGISTRATION_PAGE_PATH);
    }

    /**
     * Method for authorization.
     *
     * @param request
     * @return String page
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(final HttpServletRequest request) {
        final String email = request.getParameter(Parameters.EMAIL);
        final String password = request.getParameter(Parameters.PASSWORD);
        String page;

        try {
            if (userManagement.authorized(email, password)) {
                final HttpSession session = request.getSession();
                final User user = userManagement.getUserByEmail(email);
                session.setAttribute(Parameters.USER_ROLE, user.getRole());
                session.setAttribute(Parameters.USER, user);
                if (Parameters.ADMIN.equals(user.getRole().toString())) {
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
                } else {
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);
                }
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_LOGIN_OR_PASSWORD, MessageManager.INSTANCE.getProperty(MessageConstants.WRONG_LOGIN_OR_PASSWORD));
            }
        } catch (ServiceException e) {
            //logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }

    /**
     * Method returns page log out to main page.
     *
     * @param request
     * @return String page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(final HttpServletRequest request) {
        final String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
        request.getSession().invalidate();
        return page;
    }

    /**
     * Method returns registration page.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(final HttpServletRequest request) {
        String page;
        final String name = request.getParameter(Parameters.NAME);
        final String surname = request.getParameter(Parameters.SURNAME);
        final String email = request.getParameter(Parameters.EMAIL);
        final String password = request.getParameter(Parameters.PASSWORD);
        RoleType role;
        try {
            if (null != request.getParameter(Parameters.ROLE)) {
                role = RoleType.ADMIN;
            } else {
                role = RoleType.USER;
            }
            if (!name.isEmpty() & !surname.isEmpty() & !email.isEmpty() & !password.isEmpty()) {
                if (userManagement.isNewUser(email)) {
                    final User user = new User();
                    user.setName(name);
                    user.setSurname(surname);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setRole(role);
                    userManagement.save(user);
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
                    request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.SUCCESS_REGISTRATION));
                } else {
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.REGISTRATION_PAGE_PATH);
                    request.setAttribute(Parameters.ERROR_USER_EXISTS, MessageManager.INSTANCE.getProperty(MessageConstants.USER_EXSISTS));
                }
            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.REGISTRATION_PAGE_PATH);
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.EMPTY_FIELDS));
            }
        } catch (ServiceException e) {
            // logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        } catch (NumberFormatException e) {
            //logger.writeLog(e.getMessage());
            request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.REGISTRATION_PAGE_PATH);
        } catch (NullPointerException e) {
            //logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
        }
        return page;
    }
}
