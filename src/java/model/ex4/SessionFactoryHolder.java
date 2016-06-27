package model.ex4;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * the Session Factory Holder class
 * @author user
 */
public class SessionFactoryHolder {

    /**
     * creates a session factory using the configuration files
     */
    public static SessionFactory sessionFactory
            = new Configuration().configure().buildSessionFactory();
}
