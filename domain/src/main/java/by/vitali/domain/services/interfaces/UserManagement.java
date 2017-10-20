package by.vitali.domain.services.interfaces;


import by.vitali.infrastructure.model.User;

public interface UserManagement extends GeneralManagement<User> {

    boolean isAuthorized(String login, String email) throws Exception;

    String checkRole(String login) throws Exception;

    User getUserByEmail(String login) throws Exception;

    boolean isNewUser(String login) throws Exception;
}
