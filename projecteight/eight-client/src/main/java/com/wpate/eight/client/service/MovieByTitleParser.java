package com.wpate.eight.client.service;

import com.wpate.eight.client.model.Movie;
import com.wpate.eight.client.rest.MovieRestClient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(staticName = "of")
public class MovieByTitleParser {

    private String movies;
    private MovieRestClient movieRestClient;

    public Set<Movie> parse() {
        return Arrays.stream(movies.split(","))
                .map(t -> movieRestClient.getMovieApi().moviesByTitle(t))
                .filter(t -> !t.isEmpty())
                .map(t -> t.get(0))
                .collect(Collectors.toSet());
    }

}
