package com.wpate.eight.client.view;

import com.wpate.eight.client.model.Movie;
import com.wpate.eight.client.model.Person;
import com.wpate.eight.client.rest.PersonRestClient;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.wpate.eight.client.rest.ResponseUtils.checkResponse;

@Getter
@Setter
@SessionScoped
@Named("PersonUpdateView")
public class PersonUpdateView implements Serializable {

    private long id;
    private String name;
    private int age;
    private byte[] image;
    private Set<Movie> movies = new HashSet<>();

    @Inject
    private PersonRestClient personRestClient;

    public String startUpdating(Person person) {
        id = person.getId();
        name = person.getName();
        age = person.getAge();
        image = person.getImage();
        movies = person.getFavouriteMovies();
        return "updatePerson";
    }

    public String update() {
        checkResponse(personRestClient.getPersonApi().updatePerson(id, new Person(id, name, age, image, movies)));
        return "index";
    }


}
