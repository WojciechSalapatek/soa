package com.wpate.eight.client.rest;

import com.wpate.eight.client.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
public interface PersonApi {

    @GET
    @Path("/people")
    @Produces(MediaType.APPLICATION_JSON)
    List<Person> people();

    @GET
    @Path("/people/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getPerson(@PathParam("id") long id);

    @GET
    @Path("/people/{id}/avatar")
    @Produces(MediaType.APPLICATION_JSON)
    Response getPersonAvatar(@PathParam("id") long id);

    @GET
    @Path("/people/{id}/downloadableAvatar")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    Response getPersonDownloadableAvatar(@PathParam("id") long id);

    @GET
    @Path("/people/{id}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    Response getPersonMovies(@PathParam("id") long id);

    @DELETE
    @Path("/people/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response deletePerson(@PathParam("id") long id);

    @PUT
    @Path("/people/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response updatePerson(@PathParam("id") long id, Person aPerson);

    @POST
    @Path("/people")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response insertPerson(Person aPerson);

}
