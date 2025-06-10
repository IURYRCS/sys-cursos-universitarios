package org.utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "outroPU"; // Nome da unidade de persistência no persistence.xml
    private static EntityManagerFactory factory;
    public static EntityManager getEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory.createEntityManager();
    }
    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}