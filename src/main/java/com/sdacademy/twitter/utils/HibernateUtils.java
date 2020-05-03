package com.sdacademy.twitter.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class contains utilities used to connect to the database via Hibernate
 */
public final class HibernateUtils {

    private HibernateUtils() {
    }

    private final static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    private static Session session;

    /**
     * Method returns Hibernate Session instance
     *
     * @return
     */
    public static Session getHibernateSession() {
        if (session == null) {
            session = sf.openSession();
        }
        return session;
    }
}
