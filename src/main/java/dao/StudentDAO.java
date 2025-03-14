package dao;

import model.Aikido_ENUM;
import model.Student;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO() {
        super(Student.class);
    }

    public List<Student> findStudentByRank(Aikido_ENUM rank) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM Student s " +
                                    "WHERE s.rank = :rank", Student.class)
                    .setParameter("rank", rank)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
