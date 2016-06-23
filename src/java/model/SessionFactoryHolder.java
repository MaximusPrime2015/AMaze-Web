package model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author user
 */
public class SessionFactoryHolder {
    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
}
