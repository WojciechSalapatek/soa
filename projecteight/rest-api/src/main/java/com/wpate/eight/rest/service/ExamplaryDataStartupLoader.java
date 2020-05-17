package com.wpate.eight.rest.service;

import com.wpate.eight.rest.domain.Movie;
import com.wpate.eight.rest.domain.Person;
import com.wpate.eight.rest.repository.MovieRepository;
import com.wpate.eight.rest.repository.PersonRepository;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toSet;

@Startup
@Singleton
public class ExamplaryDataStartupLoader {

    @Inject
    private PersonRepository personRepository;

    @Inject
    private MovieRepository movieRepository;

    private static final String AVATAR = "avatar.png";

    private Function<Long, String> uriProducer = (id) -> "http://localhost:8080/eight/api/movies/" + id;

    @PostConstruct
    void setupExampleData() {
        setupSomePeople();
        setupSomeMovies();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void setupSomePeople() {
        personRepository.insert(Person.builder().name("Perzon1").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon2").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon3").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon4").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon5").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon6").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon7").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon8").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
        personRepository.insert(Person.builder().name("Perzon9").age((int) (100 * Math.random())).image(imageToBytes()).build(), 0);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void setupSomeMovies() {
        Set<Long> personIds = personRepository.findAll().stream().map(Person::getId).collect(toSet());
        movieRepository.insert(Movie.of(0, "Gwiezdny tron1", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron2", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron3", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron4", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron5", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron6", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron7", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron8", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        movieRepository.insert(Movie.of(0, "Gwiezdny tron9", "someUri", getRandomIds(personIds).stream().map(i -> personRepository.findById(i).get()).collect(Collectors.toList())), 0);
        setupMoviesUris();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void setupMoviesUris() {
        movieRepository.findAll().forEach(m -> m.setUri(uriProducer.apply(m.getId())));
    }

    private long getRandomId(Set<Long> ids, String seed) {
        return ids.stream().skip(Math.abs(seed.hashCode()) % ids.size()).findFirst().get();
    }

    private long getRandomId(Set<Long> ids) {
        return ids.stream().skip((int) (ids.size() * Math.random())).findFirst().get();
    }

    private Set<Long> getRandomIds(Set<Long> ids) {
        return LongStream.of((int) (5 * Math.random())).boxed().map(l -> ids.stream().skip((int) (ids.size() * Math.random())).findFirst().get()).collect(toSet());
    }

    private long getUniqueRandomId(Set<Long> ids) {
        long res = ids.stream().skip((int) (ids.size() * Math.random())).findFirst().get();
        ids.remove(res);
        return res;
    }

    @SneakyThrows
    private byte[] imageToBytes() {
        BufferedImage bImage = ImageIO.read(getClass().getClassLoader().getResource(AVATAR).openStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        return bos.toByteArray();
    }
}
