package by.vitali.domain.services;

import by.vitali.domain.services.exceptions.ServiceException;
import by.vitali.infrastructure.model.User;
import by.vitali.infrastructure.repository.UserRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(final User user) throws ServiceException {
        try {
            if (user == null) {
                throw new IllegalArgumentException("User must not be null.");
            }
            userRepository.save(user);
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void update(final User type) throws ServiceException {
        throw new IllegalStateException("User update can't be call");
    }

    @Override
    public User read(final Long id) throws ServiceException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("User id must not be null.");
            }
            return userRepository.read(id, User.class);
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public User getUserByEmail(final String email) throws ServiceException {
        try {
            if (email == null) {
                throw new IllegalArgumentException("Email must not be null.");
            }
            return userRepository.getUserByEmail(email);
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean authorized(final String email, final String password) throws ServiceException {
        try {
            if (email == null || password == null) {
                throw new IllegalArgumentException("Email, password must not be null.");
            }
            boolean isauthorized = false;
            final User user = userRepository.getUserByEmailAndPassword(email, password);
            if (user != null) {
                isauthorized = true;
            }
            return isauthorized;
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public String checkRole(final String email) throws ServiceException {
        try {
            if (email == null) {
                throw new IllegalArgumentException("Email must not be null.");
            }
            final User user = userRepository.getUserByEmail(email);
            final String roleType = user.getRole().toString();
            return roleType;
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean isNewUser(final String email) throws ServiceException {
        try {
            if (email == null) {
                throw new IllegalArgumentException("Email must not be null.");
            }
            boolean isNew = true;
            final User user = userRepository.getUserByEmail(email);
            if (user != null) {
                isNew = false;
            }
            return isNew;
        } catch (HibernateException e) {
            //logger
            throw new ServiceException(e.getMessage());
        }
    }
}



