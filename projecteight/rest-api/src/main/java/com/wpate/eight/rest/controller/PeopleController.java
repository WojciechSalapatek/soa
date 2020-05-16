package com.wpate.eight.rest.controller;

import com.wpate.eight.rest.domain.Person;
import com.wpate.eight.rest.repository.PersonRepository;
//import io.swagger.annotations.Api;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static com.wpate.eight.rest.controller.ResponseFactory.*;
import static java.util.Objects.isNull;

//@Api
@Path("")
public class PeopleController {

    @Inject
    private PersonRepository personRepository;

    @GET
    @Path("/osoby")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeople() {
        return seeOther("/people");
    }

    @GET
    @Path("/people")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> people() {
        return personRepository.findAll();
    }

    @GET
    @Path("/people/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isEmpty() ? badRequest() : ok(person.get());
    }

    @GET
    @Path("/people/{id}/avatar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonAvatar(@PathParam("id") long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isEmpty() ? badRequest() : ok(person.get().getImage());
    }

    @GET
    @Path("/people/{id}/downloadableAvatar")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getPersonDownloadableAvatar(@PathParam("id") long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isEmpty() ? badRequest() : ok(person.get().getImageStreamingOutput());
    }

    @GET
    @Path("/people/{id}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonMovies(@PathParam("id") long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isEmpty() ? badRequest() : ok(person.get().getFavouriteMovies());
    }

    @DELETE
    @Path("/people/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") long id) {
        personRepository.deleteById(id);
        return noContent();
    }

    @PUT
    @Path("/people/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") long id, Person aPerson) {
        if (isNull(aPerson) || aPerson.getId() == 0) return badRequest();
        return personRepository.update(aPerson) ? noContent() : badRequest();
    }

    @POST
    @Path("/people")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertPerson(Person aPerson) {
        personRepository.insert(aPerson, 0);
        return created();
    }

}
