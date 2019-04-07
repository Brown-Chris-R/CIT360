package utiltracker.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateHelper {

    // calls buildSessionFactory and stores in sessionFactory.  SessionFactory is heavyweight object, usually only
    // one per application. Shutdown at end of program
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // builds sessionFactory. First Creates ServiceRegistry from config file, then creates Metadata from service Registry.
    //Then use method in metadata to build the SessionFactory.
    private static SessionFactory buildSessionFactory() {
        try {
            // Create serviceRegistry from hibernate.cfg.xml
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            // create metadata source using the specified service registry.
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();



        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // close caches and connection pools
        getSessionFactory().close();
    }
}