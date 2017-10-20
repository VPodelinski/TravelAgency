package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.model.User;

import java.util.List;

public class UserManager implements UserManagement {

    @Override
    public boolean isAuthorized(String login, String email) throws Exception {
        return false;
    }

    @Override
    public String checkRole(String login) throws Exception {
        return null;
    }

    @Override
    public User getUserByEmail(String login) throws Exception {
        return null;
    }

    @Override
    public boolean isNewUser(String login) throws Exception {
        return false;
    }

    @Override
    public void save(User type) throws Exception {

    }

    @Override
    public void update(User type) throws Exception {

    }

    @Override
    public User read(int id) throws Exception {
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        return null;
    }
}
