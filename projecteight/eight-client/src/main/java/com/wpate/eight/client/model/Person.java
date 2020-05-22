package com.wpate.eight.client.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private byte[] image;
    private Set<Movie> favouriteMovies = new HashSet<>();

    public String favouriteMoviesToString() {
        return moviesToString(favouriteMovies);
    }

    public static String moviesToString(Set<Movie> movies) {
        return movies.stream().map(Movie::getTitle).collect(Collectors.joining(","));
    }

}

