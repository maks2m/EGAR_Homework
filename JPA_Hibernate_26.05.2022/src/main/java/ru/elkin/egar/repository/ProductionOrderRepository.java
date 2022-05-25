package ru.elkin.egar.repository;

import ru.elkin.egar.entity.ProductionOrder;
import ru.elkin.egar.util.EmfUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductionOrderRepository implements RepositoryInterface<ProductionOrder> {
    private final EntityManagerFactory entityManagerFactory = EmfUtil.entityManagerFactory();

    @Override
    public List<ProductionOrder> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<ProductionOrder> typedQuery = entityManager.createNamedQuery(
                "find_all_production_order",
                ProductionOrder.class);
        return typedQuery.getResultList();
    }

    @Override
    public ProductionOrder findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(ProductionOrder.class, id);
    }

    @Override
    public ProductionOrder save(ProductionOrder entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ProductionOrder newEntity;
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
        ProductionOrder entity = entityManager.find(ProductionOrder.class, id);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
