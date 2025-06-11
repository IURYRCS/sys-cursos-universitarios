package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.model.Disciplina;
import org.model.Professor;
import org.utils.JPAUtil;

import java.util.List;

public class ProfessorDAO {

    public void create(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(professor);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(professor);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Professor professor) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        professor = em.merge(professor);
        em.remove(professor);
        em.getTransaction().commit();
        em.close();
    }

    public Professor findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Professor professor = em.find(Professor.class, id);
        em.close();
        return professor;
    }

    public List<Professor> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Professor> query = em.createQuery("SELECT p FROM Professor p", Professor.class);
        List<Professor> professores = query.getResultList();
        em.close();
        return professores;
    }

    public void adicionarDisciplina(Professor professor, Long disciplinaId) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Professor professorManaged = em.find(Professor.class, professor.getId());
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Disciplina disciplina = disciplinaDAO.findById(disciplinaId);

        if (professorManaged != null && disciplina != null) {
            professorManaged.getDisciplinas().add(disciplina);
            em.merge(professorManaged);
        }

        em.getTransaction().commit();
        em.close();
    }

    public void removerDisciplina(Professor professor, Long disciplinaId) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Professor professorManaged = em.find(Professor.class, professor.getId());
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Disciplina disciplina = disciplinaDAO.findById(disciplinaId);

        if (professorManaged != null && disciplina != null) {
            professorManaged.getDisciplinas().remove(disciplina);
            em.merge(professorManaged);
        }

        em.getTransaction().commit();
        em.close();
    }
}
