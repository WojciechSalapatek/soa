package com.wpate.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class Book {

    private String id;
    private String title;
    private String author;
    private BookType type;
    private double price;
    private Currency currency;
    private int pages;

}
