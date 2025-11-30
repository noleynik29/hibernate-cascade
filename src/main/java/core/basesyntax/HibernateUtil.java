package core.basesyntax;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            System.out.println(">>> Loading Hibernate configuration...");
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            System.out.println(">>> Hibernate initialized.");
            return factory;
        } catch (Throwable e) {
            System.out.println(">>> Hibernate initialization FAILED:");
            e.printStackTrace();
            throw new RuntimeException("Can't create session factory ", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
