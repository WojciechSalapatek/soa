package com.wpate.projectfive.repository;

import com.wpate.projectfive.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(long id);

    void update(Book book);

    boolean deleteById(long id);
}
