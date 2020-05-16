package com.wpate.eight.rest.service;

import com.wpate.eight.rest.domain.Movie;
import com.wpate.eight.rest.repository.MovieRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class MovieService {

    @Inject
    private MovieRepository movieRepository;

    public List<Movie> getByTitle(String title) {
        return movieRepository.findAll().stream().filter(m -> m.getTitle().equals(title)).collect(Collectors.toList());
    }

}
