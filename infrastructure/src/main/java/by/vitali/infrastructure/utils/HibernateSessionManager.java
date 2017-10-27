package by.vitali.infrastructure.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Hibernate utils.
 */
@Component
public class HibernateSessionManager {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateSessionManager(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @return session
     */
    public Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (org.hibernate.HibernateException e) {
            session = sessionFactory.openSession();
        }
        return session;
    }
}
