package com.wpate.eight.rest.controller;

import com.wpate.eight.rest.domain.Movie;
import com.wpate.eight.rest.repository.MovieRepository;
import com.wpate.eight.rest.service.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.wpate.eight.rest.controller.ResponseFactory.*;
import static java.util.Objects.isNull;

//import io.swagger.annotations.Api;

//@Api
@Path("")
public class MovieController {


    private Function<Long, String> uriProducer = (id) -> "http://localhost:8080/eight/api/movies/" + id;


    @Inject
    private MovieRepository movieRepository;

    @Inject
    private MovieService movieService;

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> moviesByTitle(@QueryParam("title") String title) {
        return isNull(title) ? movieRepository.findAll() : movieService.getByTitle(title);
    }

    @DELETE
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") long id) {
        movieRepository.deleteById(id);
        return noContent();
    }

    @GET
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMovie(@PathParam("id") long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) return badRequest();
        return ok(movie.get());
    }

    @PUT
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") long id, Movie movie) {
        if (isNull(movie) || movie.getId() == 0) return badRequest();
        return movieRepository.update(movie) ? noContent() : badRequest();
    }

    @POST
    @Path("/movies/{id}/actions/updateUri")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovieUri(@PathParam("id") long id, String newUri) {
        Optional<Movie> toUpdate = movieRepository.findById(id);
        if (isNull(newUri) || toUpdate.isEmpty()) return badRequest();
        toUpdate.get().setUri(newUri);
        return movieRepository.update(toUpdate.get()) ? noContent() : badRequest();
    }

    @POST
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertMovie(Movie movie) {
        movieRepository.insert(movie, 0);
        if (isNull(movie.getUri())) {
            movie.setUri(uriProducer.apply(movie.getId()));
            movieRepository.update(movie);
        }
        return created();
    }

}
