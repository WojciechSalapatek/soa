package com.wpate.eight.client.rest;

import com.wpate.eight.client.model.Movie;
import com.wpate.eight.client.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Path("")
public interface MovieApi {

    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    List<Movie> moviesByTitle(@QueryParam("title") String title);

    @GET
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response getMovie(@PathParam("id") long id);

    @DELETE
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteMovie(@PathParam("id") long id);

    @PUT
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateMovie(@PathParam("id") long id, Movie movie);

    @POST
    @Path("/movies/{id}/actions/updateUri")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateMovieUri(@PathParam("id") long id, String newUri);

    @POST
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response insertMovie(Movie movie);

}
