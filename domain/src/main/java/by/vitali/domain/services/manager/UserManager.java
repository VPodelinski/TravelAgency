package by.vitali.domain.services.manager;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.domain.services.management.UserManagement;
import by.vitali.infrastructure.exceptions.DaoException;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User manager.
 */
@Service
@Transactional
public class UserManager implements UserManagement {

    final private UserRepository userRepository;

    @Autowired
    public UserManager(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(final User type) throws ServiceException {
        try {
            userRepository.save(type);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final User type) throws ServiceException {
        throw new IllegalStateException("User update can't be call");
    }

    @Override
    public User read(final long id) throws ServiceException {
        try {
            return userRepository.read(id, User.class);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userRepository.getAll(User.class);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User getUserByEmail(final String email) throws ServiceException {
        try {
            return userRepository.getUserByEmail(email);
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean authorized(final String email, final String password) throws ServiceException {
        try {
            boolean isauthorized = false;
            final User user = userRepository.getUserByEmailAndPassword(email, password);
            if (user != null) {
                isauthorized = true;
            }
            return isauthorized;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public String checkRole(final String email) throws ServiceException {
        //String or RoleType
        try {
            final User user = userRepository.getUserByEmail(email);
            final String roleType = user.getRole().toString();
            return roleType;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean isNewUser(final String email) throws ServiceException {
        try {
            boolean isNew = true;
            final User user = userRepository.getUserByEmail(email);
            if (user != null) {
                isNew = false;
            }
            return isNew;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }
}



