package volunteerhub;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.collection.internal.PersistentBag;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class TryMe {
    public static void main(String[] args) {
            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Organization.class)
                    .addAnnotatedClass(Opportunities.class)
                    .addAnnotatedClass(Volunteer.class)
                    .buildSessionFactory();

            Session session = sessionFactory.getCurrentSession();

            try {
                User user = new User();
                List<Organization> organizations = new PersistentBag();

                // Get user where userid = 7, and get any associated organizations for that user
                session.beginTransaction();
                user = session.get(User.class, 7);
                organizations = user.getOrganizations();
                session.getTransaction().commit();

                // Show user name and Organization list for that user
                System.out.println(user.getName());
                System.out.println("Belongs to these Organizations: ");
                for (Organization org : organizations) {
                    System.out.println(org.getName());
                }

            } finally {
                session.close();
                sessionFactory.close();
            }
        }

}