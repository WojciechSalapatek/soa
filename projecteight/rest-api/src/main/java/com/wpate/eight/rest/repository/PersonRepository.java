package com.wpate.eight.rest.repository;

import com.wpate.eight.rest.domain.Person;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.OptimisticLockException;

@Stateless
public class PersonRepository extends GenericCrudRepository<Person>{

    @Getter
    private final String selectAllQuery = "SELECT a FROM Person a";

    public PersonRepository() {
        super(Person.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(Person person){
        Person personToUpdate = startOptimisticUpdating(person.getId());
        personToUpdate.setAge(person.getAge());
        personToUpdate.setName(person.getName());
        personToUpdate.setImage(person.getImage());
        personToUpdate.setFavouriteMovies(person.getFavouriteMovies());
        try {
            manager.merge(personToUpdate);
            return true;
        } catch (OptimisticLockException e){
            return false;
        }
    }

}
