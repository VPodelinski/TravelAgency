package by.it_academy.agency.controller;

import by.it_academy.agency.beans.User;
import by.it_academy.agency.constants.MessageConstants;
import by.it_academy.agency.constants.PagePathConstants;
import by.it_academy.agency.constants.Parameters;
import by.it_academy.agency.exceptions.ServiceException;
import by.it_academy.agency.logger.logger;
import by.it_academy.agency.managers.ConfigurationManager;
import by.it_academy.agency.managers.MessageManager;
import by.it_academy.agency.services.interfaces.IRoleService;
import by.it_academy.agency.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GuestController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showStartPage() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String goToRegistration() {
        return ConfigurationManager.INSTANCE.getProperty(PagePathConstants.REGISTRATION_PAGE_PATH);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        String page;

        try {
            if (userService.isAuthorized(login, password)) {
                HttpSession session = request.getSession();
                User user = userService.getUserByLogin(login);
                String role = userService.checkRole(login);
                session.setAttribute(Parameters.USER_ROLE, role);
                session.setAttribute(Parameters.USER, user);
                if (role.equals(Parameters.ADMIN))
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ADMIN_PAGE_PATH);
                else
                    page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.USER_PAGE_PATH);

            } else {
                page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_LOGIN_OR_PASSWORD, MessageManager.INSTANCE.getProperty(MessageConstants.WRONG_LOGIN_OR_PASSWORD));
            }
        } catch (ServiceException e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        String page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
        request.getSession().invalidate();
        return page;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(HttpServletRequest request) {
        String page;
        String name = request.getParameter(Parameters.NAME);
        String surname = request.getParameter(Parameters.SURNAME);
        String email = request.getParameter(Parameters.EMAIL);
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        int fkRole;
        try {
            if (null != request.getParameter(Parameters.ROLE))
                fkRole = roleService.getEntityByRole(Parameters.ADMIN).getId();
            else
                fkRole = roleService.getEntityByRole(Parameters.USER).getId();

            if (!name.isEmpty() & !surname.isEmpty() & !email.isEmpty() & !login.isEmpty() & !password.isEmpty()) {
                if (userService.isNewUser(login)) {
                    User user = new User();
                    user.setName(name);
                    user.setSurname(surname);
                    user.setEmail(email);
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setRole(roleService.getById(fkRole));
                    userService.add(user);
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
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.INSTANCE.getProperty(MessageConstants.ERROR_DATABASE));
        } catch (NumberFormatException e) {
            logger.writeLog(e.getMessage());
            request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.INSTANCE.getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.REGISTRATION_PAGE_PATH);
        } catch (NullPointerException e) {
            logger.writeLog(e.getMessage());
            page = ConfigurationManager.INSTANCE.getProperty(PagePathConstants.INDEX_PAGE_PATH);
        }
        return page;
    }
}