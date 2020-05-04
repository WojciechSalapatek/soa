package com.wpate.projectsix.repository;

import com.wpate.projectsix.domain.BookAuthor;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.OptimisticLockException;

@Stateless
public class AuthorRepository extends GenericCrudRepository<BookAuthor> {

    @Getter
    private final String selectAllQuery = "SELECT a FROM BookAuthor a";

    public AuthorRepository() {
        super(BookAuthor.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(BookAuthor entity, BookAuthor newData) {
        entity.setName(newData.getName());
        try {
            manager.merge(entity);
            return true;
        } catch (OptimisticLockException e){
            return false;
        }
    }

}
