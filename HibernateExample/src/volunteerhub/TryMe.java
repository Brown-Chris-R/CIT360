package volunteerhub;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.collection.internal.PersistentBag;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

// Demonstrate CRUD operations using fully modeled 4-table database with relationships
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
                User newUser = new User();
                List<Organization> organizations = new PersistentBag();
                Organization organization = new Organization();
                Opportunities opportunities = new Opportunities();
                Volunteer volunteer = new Volunteer();
                int tempUserIdent;
                String tempUserName;
                byte admin = 1;
                byte notAdmin = 0;

                // Demonstrate the R - Get user where userid = 7, and get any associated organizations for that user
                session.beginTransaction();
                user = session.get(User.class, 7);
                organizations = user.getOrganizations();

                // Demonstrate the C - Create a new Volunteer record and attach it to opportunityid 5
                opportunities.setOpportunityId(5);
                volunteer = new Volunteer("Test Volunteer", "Test inserting a volunteer","test@volunteer.com","1112223333",'Y', opportunities);
                session.persist(volunteer);

                // Setup to demonstrate U and D - insert new user and show the resulting user identity number
                newUser = new User("TestUser","This is a test user","Test User", notAdmin,'Y');
                session.persist(newUser);
                tempUserIdent = newUser.getUserId();
                tempUserName = newUser.getName();
                System.out.println("User identity for " + tempUserName + " is " + tempUserIdent);

                // Demonstrate the U - Update the user name of the user just created
                newUser.setName("Changed User Name");
                session.update(newUser);

                tempUserName = newUser.getName();
                System.out.println("User identity for " + tempUserName + " is " + tempUserIdent);

                // Demonstrate the D - Delete user just created
                session.delete(newUser);

                // Commit the transactions
                session.getTransaction().commit();

                // Show user name and Organization list for the original retrieved user
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