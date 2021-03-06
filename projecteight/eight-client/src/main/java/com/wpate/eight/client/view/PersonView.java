package com.wpate.eight.client.view;

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
import java.util.List;

import static com.wpate.eight.client.rest.ResponseUtils.checkResponse;
import static java.util.Objects.isNull;

@Getter
@Setter
@SessionScoped
@Named("PersonView")
public class PersonView implements Serializable {

    private String newName;
    private Integer newAge;
    private Part newFile;
    private String unparsedMovies;

    @Inject
    private PersonRestClient personRestClient;

    @Inject
    private MovieRestClient movieRestClient;

    public List<Person> getAllPeople() {
        return personRestClient.getPersonApi().people();
    }

    public String delete(long personId) {
        checkResponse(personRestClient.getPersonApi().deletePerson(personId));
        return "index";
    }

    @SneakyThrows
    public String addPerson() {
        byte[] file = isNull(newFile) ? null : newFile.getInputStream().readAllBytes();
        checkResponse(personRestClient.getPersonApi().insertPerson(new Person(0L, newName, newAge, file, MovieByTitleParser.of(unparsedMovies, movieRestClient).parse())));
        return "index";
    }

    @SneakyThrows
    public byte[] getPersonImage(long personId) {
        Response response = personRestClient.getPersonApi().getPersonAvatar(personId);
        checkResponse(response);
        return response.readEntity(byte[].class);
    }


}
