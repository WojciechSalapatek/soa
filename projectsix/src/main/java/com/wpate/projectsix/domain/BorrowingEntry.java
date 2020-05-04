package com.wpate.projectsix.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "LAB6_BORROWING")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class BorrowingEntry implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader")
    private Reader reader;

    @Column(name = "borrow_timestamp", nullable = false)
    private LocalDate borrowDate;


    @Column(name = "return_timestamp")
    private LocalDate returnDate;

}
