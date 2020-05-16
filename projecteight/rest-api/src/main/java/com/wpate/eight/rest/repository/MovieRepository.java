package com.wpate.eight.rest.repository;

import com.wpate.eight.rest.domain.Movie;
import com.wpate.eight.rest.domain.Person;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.OptimisticLockException;

@Stateless
public class MovieRepository extends GenericCrudRepository<Movie> {

    @Getter
    private final String selectAllQuery = "SELECT a FROM Movie a";

    public MovieRepository() {
        super(Movie.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(Movie movie){
        Movie movieToUpdate = startOptimisticUpdating(movie.getId());
        movieToUpdate.setTitle(movie.getTitle());
        movieToUpdate.setUri(movie.getUri());
        movieToUpdate.setPersons(movie.getPersons());
        try {
            manager.merge(movieToUpdate);
            return true;
        } catch (OptimisticLockException e){
            return false;
        }
    }

}
