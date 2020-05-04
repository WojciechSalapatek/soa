package com.wpate.projectsix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "LAB6_BOOK")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Book implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private BookAuthor author;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @OneToOne(mappedBy = "book")
    @JoinColumn(name = "borrowing_entry")
    private BorrowingEntry borrowingEntry;

    @Version
    private int version;

    public Pair<String, String> getTitleAndAuthor(){
        return Pair.of(title, author.getName());
    }

}
