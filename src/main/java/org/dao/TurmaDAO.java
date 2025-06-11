package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.model.Turma;

import java.util.List;

public class TurmaDAO {

    private static final String PERSISTENCE_UNIT_NAME = "seu_persistence_unit_name";
    private static EntityManagerFactory factory;

    public TurmaDAO() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
    }

    public void create(Turma turma) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(turma);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Turma findById(Long id) {
        EntityManager em = factory.createEntityManager();
        Turma turma = null;
        try {
            turma = em.find(Turma.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return turma;
    }

    public List<Turma> findAll() {
        EntityManager em = factory.createEntityManager();
        List<Turma> turmas = null;
        try {
            turmas = em.createQuery("SELECT t FROM Turma t", Turma.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return turmas;
    }

    public void update(Turma turma) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            turma = em.merge(turma);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Turma turma = em.find(Turma.class, id);
            if (turma != null) {
                em.remove(turma);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Turma turma) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(turma)) {
                turma = em.merge(turma);
            }
            em.remove(turma);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}