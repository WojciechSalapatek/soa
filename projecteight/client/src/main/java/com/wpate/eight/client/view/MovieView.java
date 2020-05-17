package com.wpate.eight.client.view;

import com.wpate.eight.client.model.Movie;
import com.wpate.eight.client.model.Person;
import com.wpate.eight.client.rest.MovieRestClient;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

import static com.wpate.eight.client.rest.ResponseUtils.checkResponse;
import static java.util.Collections.emptySet;
import static java.util.Objects.isNull;

@Getter
@Setter
@SessionScoped
@Named("MovieView")
public class MovieView implements Serializable {

    private String newMovie;

    @Inject
    private MovieRestClient movieRestClient;

    public List<Movie> getAllMovies() {
        return movieRestClient.getMovieApi().moviesByTitle(null);

    }

    public String delete(long movieId) {
        checkResponse(movieRestClient.getMovieApi().deleteMovie(movieId));
        return "index";
    }

    @SneakyThrows
    public String addMovie() {
        checkResponse(movieRestClient.getMovieApi().insertMovie(new Movie(0L, newMovie, null, emptySet())));
        return "index";
    }


}
