package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.model.Curso;
import org.utils.JPAUtil;

import java.util.List;

public class CursoDAO {

    public void create(Curso curso) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Curso curso) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Curso curso) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        curso = em.merge(curso); // Garante que a entidade está no contexto de persistência
        em.remove(curso);
        em.getTransaction().commit();
        em.close();
    }

    public Curso findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Curso curso = em.find(Curso.class, id);
        em.close();
        return curso;
    }

    public List<Curso> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
        List<Curso> cursos = query.getResultList();
        em.close();
        return cursos;
    }
}
