package by.vitali.infrastructure.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateManager {
    private SessionFactory sessionFactory;

    public HibernateManager() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try{

            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            //sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

        } catch (Exception ex){
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
