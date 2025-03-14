package dao;

import jakarta.persistence.NoResultException;
import model.TrainingSession;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TrainingSessionDAO extends GenericDAO<TrainingSession> {
    public TrainingSessionDAO() {
        super(TrainingSession.class);
    }
    //JPQL
    public List<TrainingSession> findTrainingSessionsByStudent(Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT ts FROM TrainingSession ts " +
                                    "JOIN ts.attendances a " +
                                    "JOIN a.student s " +
                                    "WHERE s.id = :studentId", TrainingSession.class)
                    .setParameter("studentId", studentId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}