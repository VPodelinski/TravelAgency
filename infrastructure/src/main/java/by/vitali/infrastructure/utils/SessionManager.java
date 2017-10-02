package by.vitali.infrastructure.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionManager {

    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession() {
        return new HibernateManager().getSession();
    }

    public void closeSession() {
        session.close();
    }

    public Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
