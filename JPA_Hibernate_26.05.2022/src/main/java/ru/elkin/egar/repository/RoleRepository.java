package ru.elkin.egar.repository;

import ru.elkin.egar.entity.Role;
import ru.elkin.egar.util.EmfUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RoleRepository implements RepositoryInterface<Role>{
    private final EntityManagerFactory entityManagerFactory = EmfUtil.entityManagerFactory();

    @Override
    public List<Role> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Role> typedQuery = entityManager.createNamedQuery(
                "find_all_roles",
                Role.class);
        return typedQuery.getResultList();
    }

    @Override
    public Role findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role save(Role entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Role newEntity;
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
        Role entity = entityManager.find(Role.class, id);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public List<Role> findRoleWithUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Role> typedQuery = entityManager.createNamedQuery(
                "find_all_roles",
                Role.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("get_users_by_role"));
        return typedQuery.getResultList();
    }
}
