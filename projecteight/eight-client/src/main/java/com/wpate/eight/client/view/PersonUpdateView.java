package com.wpate.eight.client.view;

import com.wpate.eight.client.model.Movie;
import com.wpate.eight.client.model.Person;
import com.wpate.eight.client.rest.MovieRestClient;
import com.wpate.eight.client.rest.PersonRestClient;
import com.wpate.eight.client.service.MovieByTitleParser;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.wpate.eight.client.rest.ResponseUtils.checkResponse;
import static java.util.Objects.isNull;

@Getter
@Setter
@SessionScoped
@Named("PersonUpdateView")
public class PersonUpdateView implements Serializable {

    private long id;
    private String name;
    private int age;
    private byte[] image;
    private Part newFile;
    private Set<Movie> movies = new HashSet<>();
    private String unparsedMovies;

    @Inject
    private PersonRestClient personRestClient;

    @Inject
    private MovieRestClient movieRestClient;

    public String startUpdating(Person person) {
        id = person.getId();
        name = person.getName();
        age = person.getAge();
        image = getPersonImage(id);
        movies = person.getFavouriteMovies();
        unparsedMovies = Person.moviesToString(movies);
        return "updatePerson";
    }

    @SneakyThrows
    public String update() {
        byte[] newImage = isNull(newFile) ? image : newFile.getInputStream().readAllBytes();
        checkResponse(personRestClient.getPersonApi().updatePerson(id, new Person(id, name, age, newImage, MovieByTitleParser.of(unparsedMovies, movieRestClient).parse())));
        return "index";
    }

    @SneakyThrows
    public byte[] getPersonImage(long personId) {
        Response response = personRestClient.getPersonApi().getPersonAvatar(personId);
        checkResponse(response);
        return response.readEntity(byte[].class);
    }

}
