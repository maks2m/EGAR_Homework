package ru.elkin.egar.repository;

import ru.elkin.egar.Assignment;
import ru.elkin.egar.util.EmfUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class AssignmentRepository implements RepositoryInterface<Assignment> {
    private final EntityManagerFactory entityManagerFactory = EmfUtil.entityManagerFactory();

    @Override
    public List<Assignment> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Assignment> typedQuery = entityManager.createNamedQuery(
                "find_all_assignment",
                Assignment.class);
        return typedQuery.getResultList();
    }

    @Override
    public Assignment findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Assignment.class, id);
    }

    @Override
    public Assignment save(Assignment entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Assignment newEntity;
        entityManager.getTransaction().begin();
        if (entity.getId() == null) {
            entityManager.persist(entity);
            newEntity = entity;
        } else {
            newEntity = entityManager.merge(entity);
        }
        entityManager.getTransaction().commit();
        return newEntity;
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Assignment entity = entityManager.find(Assignment.class, id);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
