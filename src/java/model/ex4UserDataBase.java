package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author user
 */
public class ex4UserDataBase {
    
    private SessionFactory sessionFactory;
    
    //private HashMap<String, ex4User> users;
    
    private ex4UserDataBase() {
        //this.users = new HashMap<>();
        this.sessionFactory = SessionFactoryHolder.sessionFactory;
    }
    
    private static class Singleton {
        public static final ex4UserDataBase singleton = new ex4UserDataBase();
    }
    
    public static ex4UserDataBase getDataBase() {
        return Singleton.singleton;
    }
    
    public boolean checkIfUserExists(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean ret = false;
        try {
            User user = (User) session.get(User.class, username);
            ret = (user != null);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        //return this.users.containsKey(username);
        return ret;
    }
    
    public ex4User getUser(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ex4User ret = null;
        try {
            User user = (User) session.get(User.class, username);
            if (user != null) {
                ret = new ex4User(user.getUsername(),
                        user.getPassword(),
                        user.getName(),
                        user.getEmail(), user.getIcon());
            }
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        //return this.users.get(username);
        return ret;
    }
    
    public void addUser(String username, String password, String name,
            String email, int icon) throws Exception {
        if (this.checkIfUserExists(username))
            throw new Exception("user already in data base");
//        // add the user
//        ex4User user = new ex4User(username, password, name, email, icon);
//        this.users.put(username, user);
        
        // hibatnate code
        User userSql = new User(username, password, name, email, icon);
        
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
