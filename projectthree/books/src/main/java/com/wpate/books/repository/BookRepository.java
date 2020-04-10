package com.wpate.books.repository;

import com.wpate.books.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    void update(Book book);
}
