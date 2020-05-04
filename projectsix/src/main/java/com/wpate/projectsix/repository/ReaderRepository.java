package com.wpate.projectsix.repository;

import com.wpate.projectsix.domain.Reader;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.OptimisticLockException;

@Stateless
public class ReaderRepository extends GenericCrudRepository<Reader> {

    @Getter
    private final String selectAllQuery = "SELECT r FROM Reader r";

    public ReaderRepository() {
        super(Reader.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(Reader entity, Reader newData) {
        entity.setName(newData.getName());
        entity.setSurname(newData.getSurname());
        entity.setBorrowings(newData.getBorrowings());
        try {
            manager.merge(entity);
            return true;
        } catch (OptimisticLockException e) {
            return false;
        }
    }

}
