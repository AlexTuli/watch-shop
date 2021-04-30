package com.alex.watchshop.config.jpa;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

//@org.springframework.context.annotation.Configuration
public class JpaFactory {

    //    @Bean
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
//        EntityScanner.scanPackages("com.alex.watchshop").addTo(configuration);
        configuration.setProperties(hibernateProperties());
        return configuration.buildSessionFactory(serviceRegistry(configuration));
    }

    private static ServiceRegistry serviceRegistry(Configuration configuration) {
        return new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
    }

    private static Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.h2.Driver");
        properties.put(Environment.URL, "jdbc:h2:~/db/watch");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "sa");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.put(Environment.HBM2DDL_AUTO, "create-drop");
        properties.put(Environment.SHOW_SQL, "hibernate.show_sql");
        return properties;
    }

}
