package com.wpate.eight.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.ws.rs.core.StreamingOutput;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LAB8_PERSON")
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Column(name = "image")
    private byte[] image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "LAB8_PF",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<Movie> favouriteMovies;

    @PreRemove
    private void removePersonsFormMovies() {
        if (isNull(favouriteMovies)) return;
        for (Movie m : favouriteMovies) {
            m.setPersons(createIfNull(m.getPersons()));
            m.getPersons().remove(this);
        }
    }

    @PrePersist
    private void addPersonToMovies() {
        if (isNull(favouriteMovies)) return;
        for (Movie m : favouriteMovies) {
            m.setPersons(createIfNull(m.getPersons()));
            m.getPersons().add(this);
        }
    }

    private <T> List<T> createIfNull(List<T> nullableList) {
        return ofNullable(nullableList).orElse(new ArrayList<>());
    }

    @JsonIgnore
    public byte[] getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(byte[] image) {
        this.image = image;
    }

    @JsonIgnore
    public StreamingOutput getImageStreamingOutput() {
        return outputStream -> outputStream.write(image);
    }

}
