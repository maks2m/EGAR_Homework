package ru.elkin.egar.repository;

import ru.elkin.egar.Assignment;
import ru.elkin.egar.ProductionOrder;
import ru.elkin.egar.User;
import ru.elkin.egar.util.EmfUtil;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public User findByIdWithAllEntity(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityGraph<User> entityGraph = entityManager.createEntityGraph(User.class);
        Subgraph<ProductionOrder> productionOrderSubgraph = entityGraph.addSubgraph("productionOrders");
        entityGraph.addAttributeNodes("productionOrders");
        Subgraph<Assignment> assignmentSubgraph = productionOrderSubgraph.addSubgraph("assignment");
        productionOrderSubgraph.addAttributeNodes("assignment");
        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistence.fetchgraph", entityGraph);
        return entityManager.find(User.class, id, map);
    }
}
