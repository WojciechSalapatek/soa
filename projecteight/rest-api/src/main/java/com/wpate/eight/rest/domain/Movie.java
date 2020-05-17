package com.wpate.eight.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Table(name = "LAB8_MOVIE")
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "uri")
    private String uri;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "favouriteMovies")
    private List<Person> persons;

    @PreRemove
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeMoviesFromPeople() {
        if (isNull(persons)) return;
        for (Person p : persons) {
            p.setFavouriteMovies(createIfNull(p.getFavouriteMovies()));
            p.getFavouriteMovies().remove(this);
        }
    }

    @PrePersist
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void addMoviesToPeople() {
        if (isNull(persons)) return;
        for (Person p : persons) {
            p.setFavouriteMovies(createIfNull(p.getFavouriteMovies()));
            p.getFavouriteMovies().add(this);
        }
    }

    private <T> List<T> createIfNull(List<T> nullableList) {
        return ofNullable(nullableList).orElse(new ArrayList<>());
    }

}
