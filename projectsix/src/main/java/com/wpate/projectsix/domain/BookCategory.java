package com.wpate.projectsix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum BookCategory implements Serializable {

    ACTION("Action"),
    SCIFI("Science-Fiction"),
    DRAMA("Drama"),
    CRIME("Crime"),
    EDU("Educational");

    private String categoryName;
}
