package model.ex4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * The class User Data Base
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class UserDataBase {
    
    private final SessionFactory sessionFactory;
    
    private UserDataBase() {
        this.sessionFactory = SessionFactoryHolder.sessionFactory;
    }
    
    private static class Singleton {
        public static final UserDataBase SINGLETON = new UserDataBase();
    }
    
    /**
     * returns the singleton instance of the user data base class
     * @return the instance
     */
    public static UserDataBase getDataBase() {
        return Singleton.SINGLETON;
    }
    
    /**
     * check if the user Exists in the data base
     * @param username the user to check
     * @return true if in the database
     */
    public boolean checkIfUserExists(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean ret = false;
        // attempt to get the user from mySql
        try {
            UserHiber user = (UserHiber) session.get(UserHiber.class,
                                                    username);
            // return value is if the user was returned
            ret = (user != null);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        // close the session
        session.getTransaction().commit();
        session.close();
        return ret;
    }
    
    /**
     * gets the user from the data base with the given username
     * @param username the user to search
     * @return the User object
     */
    public User getUser(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User ret = null;
        try {
            // attempt to get the user from mySql
            UserHiber user = (UserHiber) session.get(UserHiber.class,
                                                            username);
            if (user != null) {
                // user was found , create a User object from him
                ret = new User(user.getUsername(),
                        user.getPassword(),
                        user.getName(),
                        user.getEmail(), user.getIcon());
            }
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        // close the session
        session.getTransaction().commit();
        session.close();
        return ret;
    }
    
    /**
     * adds the given user to the data base
     * @param username the username
     * @param password the password
     * @param name the name of the user
     * @param email the email of the user
     * @param icon the icon id of the user
     * @throws Exception if already in the database
     */
    public void addUser(String username, String password, String name,
            String email, int icon) throws Exception {
        if (this.checkIfUserExists(username))
            // can't add existing user to the database
            throw new Exception("user already in data base");
        UserHiber userSql = new UserHiber(username, password, name,
                                                        email, icon);
        // open mySql session
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            // add the user to mySql
            session.persist(userSql);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        // close the session
        session.getTransaction().commit();
        session.close();
    }
}
