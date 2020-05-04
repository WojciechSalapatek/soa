package com.wpate.projectsix.service;

import com.wpate.projectsix.domain.Book;
import com.wpate.projectsix.domain.CatalogEntry;
import com.wpate.projectsix.repository.BookRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Stateless
public class CatalogEntryProvider implements Serializable {

    @Inject
    private BookRepository bookRepository;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<CatalogEntry> getCatalogEntries() {
        List<Book> allBooks = bookRepository.findAll();
        List<CatalogEntry> entries = allBooks.stream()
                .filter(uniqueBooksFilter(Book::getTitleAndAuthor))
                .map(b -> CatalogEntry.builder().title(b.getTitle()).author(b.getAuthor().getName()).category(b.getCategory()).build())
                .collect(Collectors.toList());
        entries.forEach(e->{
            e.setAll(allGeneralBooks(allBooks, e.getTitle(), e.getAuthor()));
            e.setAvailable(e.getAll() - bookRepository.borrowedBooks(e.getTitle()));
        });
        return entries;
    }

    private static <T> Predicate<T> uniqueBooksFilter(Function<? super T, ?> uniqueValExtractor) {
        Set<Object> seen = new HashSet<>();
        return t -> seen.add(uniqueValExtractor.apply(t));
    }

    private int allGeneralBooks(List<Book> allBooks, String title, String author){
        return (int) allBooks.stream().filter(b-> b.getTitle().equals(title) && b.getAuthor().getName().equals(author)).count();
    }
}
