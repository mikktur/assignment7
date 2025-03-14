package dao;

import jakarta.persistence.*;

import java.util.ConcurrentModificationException;
import java.util.List;

public class GenericDAO<T> {
    private final Class<T> entityClass;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("aikidoPU");

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }



    public void save(T entity) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public T findById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.find(entityClass, id);
        }
    }
    public List<T> findAll() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
        }
    }

    public void update(T entity) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.merge(entity);
            tx.commit();
        }catch (OptimisticLockException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Optimistic locking fail");
    }catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void delete(T entity) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
