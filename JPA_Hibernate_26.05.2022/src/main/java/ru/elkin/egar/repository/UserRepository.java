package ru.elkin.egar.repository;

import ru.elkin.egar.entity.User;
import ru.elkin.egar.util.EmfUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepository implements RepositoryInterface<User>{
    private final EntityManagerFactory entityManagerFactory = EmfUtil.entityManagerFactory();

    @Override
    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<User> typedQuery = entityManager.createNamedQuery(
                "find_all_users",
                User.class);
        return typedQuery.getResultList();
    }

    @Override
    public User findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(User.class, id);
    }

    @Override
    public User save(User entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User newEntity;
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
        User entity = entityManager.find(User.class, id);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
