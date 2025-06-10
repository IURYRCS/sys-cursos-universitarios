package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.model.Disciplina;
import org.utils.JPAUtil;

import java.util.List;

public class DisciplinaDAO {

    public void create(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(disciplina);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(disciplina);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Disciplina disciplina) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        disciplina = em.merge(disciplina); // Garante que a entidade está no contexto de persistência
        em.remove(disciplina);
        em.getTransaction().commit();
        em.close();
    }

    public Disciplina findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Disciplina disciplina = em.find(Disciplina.class, id);
        em.close();
        return disciplina;
    }

    public List<Disciplina> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Disciplina> query = em.createQuery("SELECT d FROM Disciplina d", Disciplina.class);
        List<Disciplina> disciplinas = query.getResultList();
        em.close();
        return disciplinas;
    }
}
