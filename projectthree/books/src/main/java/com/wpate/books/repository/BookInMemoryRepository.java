package com.wpate.books.repository;

import com.wpate.books.model.Book;
import com.wpate.books.model.BookType;

import java.util.ArrayList;
import java.util.List;

import static com.wpate.books.model.Currency.*;
import static java.util.Objects.isNull;

public class BookInMemoryRepository implements BookRepository {

    {
        init();
    }

    private static final List<Book> BOOKS = new ArrayList<>();


    @Override
    public List<Book> findAll() {
        return BOOKS;
    }

    @Override
    public void update(Book book) {
        Book bookToUpdate = BOOKS.stream()
                .filter(b -> b.getId() == book.getId())
                .findFirst()
                .orElse(null);

        if (isNull(bookToUpdate)) BOOKS.add(book);
        else {
            BOOKS.set(BOOKS.indexOf(bookToUpdate), book);
        }
    }

    private void init(){
        update(Book.of("1", "title1", "Wpate", BookType.ACTION, 9.99, PLN, 310));
        update(Book.of("2", "title2", "Wpate", BookType.CRIME, 19.99, EUR,210));
        update(Book.of("3", "title3", "Van Sam", BookType.DRAMA, 29.99, PLN,350));
        update(Book.of("4", "title4", "Ram", BookType.EDU, 39.99, PLN,317));
        update(Book.of("5", "title5", "Cpu Gpu", BookType.ACTION, 7.99, EUR,610));
        update(Book.of("6", "title6", "Ross John", BookType.CRIME, 40.50, PLN,220));
        update(Book.of("7", "title7", "Ross John", BookType.SCIFI, 17.32, PLN,667));
        update(Book.of("8", "title8", "Ross John", BookType.SCIFI, 43.12, PLN,421));
        update(Book.of("9", "title9", "Van Sam", BookType.EDU, 60.00, PLN,320));
        update(Book.of("10", "title1", "Wpate", BookType.ACTION, 20.00, PLN,217));
        update(Book.of("11", "title2", "Wpate", BookType.DRAMA, 5.99, USD,333));
    }
}
