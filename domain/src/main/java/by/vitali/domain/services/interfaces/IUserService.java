package by.vitali.domain.services.interfaces;


import by.vitali.infrastructure.model.User;

public interface IUserService {
    boolean isAuthorized(String login, String password) throws Exception;

    String checkRole(String login) throws Exception;

    User getUserByLogin(String login) throws Exception;

    boolean isNewUser(String login) throws Exception;
}
