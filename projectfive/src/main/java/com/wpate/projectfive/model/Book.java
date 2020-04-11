package com.wpate.projectfive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "LAB5_books")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Book implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "price", nullable = false)
    private double price;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

}
