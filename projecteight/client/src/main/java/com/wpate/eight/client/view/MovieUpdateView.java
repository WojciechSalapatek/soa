package com.wpate.eight.client.view;

import com.wpate.eight.client.model.Movie;
import com.wpate.eight.client.model.Person;
import com.wpate.eight.client.rest.MovieRestClient;
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
@Named("MovieUpdateView")
public class MovieUpdateView implements Serializable {

    private long id;
    private String title;
    private String uri;
    private Set<Person> persons = new HashSet<>();

    @Inject
    private MovieRestClient movieRestClient;

    public String startUpdating(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        uri = movie.getUri();
        persons = movie.getPersons();
        return "updateMovie";
    }

    public String update() {
        checkResponse(movieRestClient.getMovieApi().updateMovie(id, new Movie(id, title, uri, persons)));
        return "index";
    }


}
