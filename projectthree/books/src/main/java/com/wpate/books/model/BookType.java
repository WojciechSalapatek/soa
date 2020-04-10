package com.wpate.books.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookType {

    ACTION("Action"),
    SCIFI("Science-Fiction"),
    DRAMA("Drama"),
    CRIME("Crime"),
    EDU("Educational");

    private String typeName;
}
