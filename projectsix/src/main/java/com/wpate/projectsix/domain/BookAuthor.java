package com.wpate.projectsix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "LAB6_BOOK_AUTHOR")
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthor implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Version
    private int version;


}
