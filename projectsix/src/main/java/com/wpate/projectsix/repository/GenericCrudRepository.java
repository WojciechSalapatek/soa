package com.wpate.projectsix.repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static javax.ejb.TransactionAttributeType.REQUIRED;

public abstract class GenericCrudRepository<EntityType> implements Serializable {

    @PersistenceContext(unitName = "library")
    protected EntityManager manager;

    protected Class<EntityType> clazz;

    public GenericCrudRepository(Class<EntityType> clazz) {
        this.clazz = clazz;
    }

    public abstract String getSelectAllQuery();


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<EntityType> findAll() {
        return manager.createQuery(getSelectAllQuery(), clazz).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Optional<EntityType> findById(long id) {
        return ofNullable(manager.find(clazz, id));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insert(EntityType entity, long id) {
        EntityType existing = manager.find(clazz, id);
        if (isNull(existing)) {
            manager.persist(entity);
        } else {
            manager.merge(entity);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public EntityType startOptimisticUpdating(long id) {
        return manager.find(clazz, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public EntityType pessimisticLock(long id) {
        return manager.find(clazz, id, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
    }

    @TransactionAttribute(REQUIRED)
    public boolean deleteById(long id) {
        Optional<EntityType> entityToRemove = findById(id);
        if (entityToRemove.isEmpty()) return false;
        manager.remove(entityToRemove.get());
        return true;
    }

    public void flush() {
        manager.flush();
    }

}
