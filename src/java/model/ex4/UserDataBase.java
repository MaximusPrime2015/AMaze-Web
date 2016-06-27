package model.ex4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * The class User Data Base
 * @author user
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
     *
     * @param username
     * @return
     */
    public boolean checkIfUserExists(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean ret = false;
        try {
            UserHiber user = (UserHiber) session.get(UserHiber.class, username);
            ret = (user != null);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return ret;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public User getUser(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User ret = null;
        try {
            UserHiber user = (UserHiber) session.get(UserHiber.class, username);
            if (user != null) {
                ret = new User(user.getUsername(),
                        user.getPassword(),
                        user.getName(),
                        user.getEmail(), user.getIcon());
            }
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return ret;
    }
    
    /**
     *
     * @param username
     * @param password
     * @param name
     * @param email
     * @param icon
     * @throws Exception
     */
    public void addUser(String username, String password, String name,
            String email, int icon) throws Exception {
        if (this.checkIfUserExists(username))
            throw new Exception("user already in data base");
        UserHiber userSql = new UserHiber(username, password, name, email, icon);
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(userSql);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
    }
}
