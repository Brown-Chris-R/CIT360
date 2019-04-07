package utiltracker.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DAOHelper {

    SessionFactory factory = null;
    Session session = null;

    private static DAOHelper single_instance = null;

    // constructor. This is private to make it a singleton. Only one instance of it.
    // constructor creates new SessionFactory.
    private DAOHelper() {
        factory = HibernateHelper.getSessionFactory();
    }

    // This method is used instead of constructor to only creat one instance of the DAOHelper.  If there is not an instance,
    // private constructor is called.  If there is, the single instance is returned.
    public static DAOHelper getInstance() {
        if (single_instance == null) {
            single_instance = new DAOHelper();
        }
        return single_instance;
    }

    // add User
    public void addUser(Users aUser) {
        session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(aUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateUser(Users aUser) {
        session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(aUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ArrayList getAllUsers() { return this.getAll("");}

    private ArrayList getAll(String whereClause) {
        session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query allUserQuery = session.createQuery("from utiltracker.model.Users order by id" + whereClause);
            List userList = allUserQuery.list();
            tx.commit();
            return new ArrayList(userList);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Users getUser(String aName, String aPassword) {
        session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from utiltracker.model.Users where user=(:name) and password=(:pass)";
            Query query = session.createQuery(sql).setParameter("name", aName).setParameter("pass", aPassword);
            System.out.println(query);
            Users theUser = (Users) query.uniqueResult();

            tx.commit();
            session.close();
            return theUser;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            session.close();
            return null;
        }
    }

    public Users getUserById(int userId) {
        session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Users theUser = session.get(Users.class, userId);
            tx.commit();
            session.close();
            return theUser;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            session.close();
            return null;
        }
    }

    public UserAddress getUserAddress(int aUserId, String aCity, String aState, String aZipcode) {
        session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "from utiltracker.model.UserAddress where user_id=(:p_userid) and city=(:p_city) and state=(:p_state) and zip=(:p_zip)";
            Query query = session.createQuery(sql).setParameter("p_userid", aUserId).setParameter("p_city", aCity).setParameter("p_state", aState).setParameter("p_zip", aZipcode);
            System.out.println(query);
            UserAddress theUserAddress = (UserAddress) query.uniqueResult();

            tx.commit();
            session.close();
            return theUserAddress;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            session.close();
            return null;
        }
    }
    // get all addresses for a specific user
    public List<String> getUserAddresses(int aUserId) {
        session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "select city from utiltracker.model.UserAddress where user_id=(:p_userid)";
            Query query = session.createQuery(sql).setParameter("p_userid", aUserId);
            System.out.println(query);
            List<String> userAddresses = (List<String>) query.getResultList();

            tx.commit();
            session.close();
            return userAddresses;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            session.close();
            return null;
        }
    }

    // add UserAddress
    public void addUserAddress(UserAddress aUserAddress) {
        session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(aUserAddress);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // add UtilityBill
    public void addUtilityBill(UtilityBill aUtilityBill) {
        session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(aUtilityBill);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}