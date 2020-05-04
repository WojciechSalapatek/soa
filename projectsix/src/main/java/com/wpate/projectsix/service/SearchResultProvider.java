package com.wpate.projectsix.service;

import com.wpate.projectsix.domain.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Stateless
public class SearchResultProvider implements Serializable {

    @PersistenceContext
    private EntityManager manager;

    public List<SearchResult> getSearchResult(SearchFilter filter) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<SearchResult> query = criteriaBuilder.createQuery(SearchResult.class);
        Root<Book> book = query.from(Book.class);
        Join<Book, BookAuthor> author = book.join("author", JoinType.LEFT);
        Join<Book, BorrowingEntry> borrowing = book.join("borrowingEntry", JoinType.LEFT);
        Join<BorrowingEntry, Reader> reader = borrowing.join("reader", JoinType.LEFT);

        List<Predicate> conditions = new ArrayList<>();

        if(!isNull(filter.getTitle())){
            conditions.add(criteriaBuilder.equal(book.get("title"), filter.getTitle()));
        }

        if(!isNull(filter.getAuthorName())){
            conditions.add(criteriaBuilder.equal(author.get("name"), filter.getAuthorName()));
        }

        if(!isNull(filter.getBookCategory())){
            conditions.add(criteriaBuilder.equal(book.get("category"), filter.getBookCategory()));
        }

        if(!isNull(filter.getIsbn())){
            conditions.add(criteriaBuilder.equal(book.get("isbn"), filter.getIsbn()));
        }

        if(!isNull(filter.getReaderName())){
            conditions.add(criteriaBuilder.equal(reader.get("name"), filter.getReaderName()));
        }

        if(!isNull(filter.getReaderSurname())){
            conditions.add(criteriaBuilder.equal(reader.get("surname"), filter.getReaderSurname()));
        }

        if(!isNull(filter.getBorrowedFrom())){
            conditions.add(criteriaBuilder.greaterThanOrEqualTo(borrowing.get("borrowDate"), LocalDate.parse(filter.getBorrowedFrom())));
        }

        if(!isNull(filter.getBorrowedTo())){
            conditions.add(criteriaBuilder.lessThanOrEqualTo(borrowing.get("borrowDate"), LocalDate.parse(filter.getBorrowedTo())));
        }

        query.multiselect(book, author.alias("author"), borrowing.alias("borrowingEntry"), reader.alias("reader"))
                .where(conditions.toArray(new Predicate[] {}));

        return manager.createQuery(query).getResultList();
    }

}
