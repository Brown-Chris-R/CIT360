package volunteerhub;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            session.beginTransaction();
            user = session.get(User.class,8);
            session.getTransaction().commit();

            System.out.println(user.getName());
        }
        finally {
            session.close();
            sessionFactory.close();
        }

    }
}
