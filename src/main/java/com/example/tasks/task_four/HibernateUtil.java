package com.example.tasks.task_four;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static void createSessionFactory(Configuration configuration) { // Для обеспечения доступа по разным конфигурациям. Для тестов h2DB для самого application - mysql
        if (sessionFactory == null) {
            try {
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}