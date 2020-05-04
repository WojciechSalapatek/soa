package com.wpate.projectsix.service;

import com.google.common.collect.ImmutableList;
import com.wpate.projectsix.domain.Book;
import com.wpate.projectsix.domain.BookAuthor;
import com.wpate.projectsix.domain.BorrowingEntry;
import com.wpate.projectsix.domain.Reader;
import com.wpate.projectsix.repository.AuthorRepository;
import com.wpate.projectsix.repository.BookRepository;
import com.wpate.projectsix.repository.BorrowingEntryRepository;
import com.wpate.projectsix.repository.ReaderRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wpate.projectsix.domain.BookCategory.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Startup
@Singleton
public class ExamplaryDataStartupLoader {

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private ReaderRepository readerRepository;

    @Inject
    private BorrowingEntryRepository borrowingEntryRepository;

    @PostConstruct
    void setupExampleData() {
        setupSomeAuthors();
        setupSomeReaders();
        setupSomeBooks();
        setupSomeBorrowings();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void setupSomeReaders() {
        Reader r1 = new Reader();
        r1.setName("Tomasz");
        r1.setSurname("Hay");
        readerRepository.insert(r1, r1.getId());
        Reader r2 = new Reader();
        r2.setName("Zbigniew");
        r2.setSurname("Bon");
        readerRepository.insert(r2, r2.getId());
        Reader r3 = new Reader();
        r3.setName("Jacek");
        r3.setSurname("Krzy");
        readerRepository.insert(r3, r3.getId());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void setupSomeAuthors() {
        BookAuthor a1 = new BookAuthor();
        a1.setName("Adam Słowacki");
        authorRepository.insert(a1, 0);
        BookAuthor a2 = new BookAuthor();
        a2.setName("Juliusz Mickiewicz");
        authorRepository.insert(a2, 0);
        BookAuthor a3 = new BookAuthor();
        a3.setName("Maria Lem");
        authorRepository.insert(a3, 0);
        BookAuthor a4 = new BookAuthor();
        a4.setName("Stanisław Konopnicki");
        authorRepository.insert(a4, 0);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void setupSomeBooks() {
        Set<Long> authorIds = authorRepository.findAll().stream().map(BookAuthor::getId).collect(Collectors.toSet());
        List<Book> books = ImmutableList.<Book>builder()
                .add(Book.builder().title("Wujowie").author(authorRepository.findById(getRandomId(authorIds, "Wujowie")).get()).isbn(randomAlphanumeric(16)).category(DRAMA).build())
                .add(Book.builder().title("Wujowie").author(authorRepository.findById(getRandomId(authorIds, "Wujowie")).get()).isbn(randomAlphanumeric(16)).category(DRAMA).build())
                .add(Book.builder().title("Wujowie").author(authorRepository.findById(getRandomId(authorIds, "Wujowie")).get()).isbn(randomAlphanumeric(16)).category(DRAMA).build())
                .add(Book.builder().title("Pan Zbigniew").author(authorRepository.findById(getRandomId(authorIds, "Pan Zbigniew")).get()).isbn(randomAlphanumeric(16)).category(ACTION).build())
                .add(Book.builder().title("Pan Zbigniew").author(authorRepository.findById(getRandomId(authorIds, "Pan Zbigniew")).get()).isbn(randomAlphanumeric(16)).category(ACTION).build())
                .add(Book.builder().title("Pan Zbigniew").author(authorRepository.findById(getRandomId(authorIds, "Pan Zbigniew")).get()).isbn(randomAlphanumeric(16)).category(ACTION).build())
                .add(Book.builder().title("Pan Zbigniew").author(authorRepository.findById(getRandomId(authorIds, "Pan Zbigniew")).get()).isbn(randomAlphanumeric(16)).category(ACTION).build())
                .add(Book.builder().title("Plastikowe Domy").author(authorRepository.findById(getRandomId(authorIds, "Plastikowe Domy")).get()).isbn(randomAlphanumeric(16)).category(SCIFI).build())
                .add(Book.builder().title("Plastikowe Domy").author(authorRepository.findById(getRandomId(authorIds, "Plastikowe Domy")).get()).isbn(randomAlphanumeric(16)).category(SCIFI).build())
                .add(Book.builder().title("Plastikowe Domy").author(authorRepository.findById(getRandomId(authorIds, "Plastikowe Domy")).get()).isbn(randomAlphanumeric(16)).category(SCIFI).build())
                .add(Book.builder().title("Plastikowe Domy").author(authorRepository.findById(getRandomId(authorIds, "Plastikowe Domy")).get()).isbn(randomAlphanumeric(16)).category(SCIFI).build())
                .add(Book.builder().title("Mały Król").author(authorRepository.findById(getRandomId(authorIds, "Mały Król")).get()).isbn(randomAlphanumeric(16)).category(EDU).build())
                .add(Book.builder().title("Na Dachu").author(authorRepository.findById(getRandomId(authorIds, "Na Dachu")).get()).isbn(randomAlphanumeric(16)).category(CRIME).build())
                .add(Book.builder().title("Na Dachu").author(authorRepository.findById(getRandomId(authorIds, "Na Dachu")).get()).isbn(randomAlphanumeric(16)).category(CRIME).build())
                .add(Book.builder().title("Na Dachu").author(authorRepository.findById(getRandomId(authorIds, "Na Dachu")).get()).isbn(randomAlphanumeric(16)).category(CRIME).build())
                .add(Book.builder().title("Na Dachu").author(authorRepository.findById(getRandomId(authorIds, "Na Dachu")).get()).isbn(randomAlphanumeric(16)).category(CRIME).build())
                .add(Book.builder().title("Fasolka").author(authorRepository.findById(getRandomId(authorIds, "Fasolka")).get()).isbn(randomAlphanumeric(16)).category(DRAMA).build())
                .add(Book.builder().title("Fasolka").author(authorRepository.findById(getRandomId(authorIds, "Fasolka")).get()).isbn(randomAlphanumeric(16)).category(DRAMA).build())
                .add(Book.builder().title("Fasolka").author(authorRepository.findById(getRandomId(authorIds, "Fasolka")).get()).isbn(randomAlphanumeric(16)).category(DRAMA).build())
                .build();
        books.forEach(b -> bookRepository.insert(b, 0));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void setupSomeBorrowings() {
        Set<Long> bookIds = bookRepository.findAll().stream().map(Book::getId).collect(Collectors.toSet());
        Set<Long> readerIds = readerRepository.findAll().stream().map(Reader::getId).collect(Collectors.toSet());
        borrowingEntryRepository.insert(BorrowingEntry.of(0, bookRepository.findById(getUniqueRandomId(bookIds)).get(), readerRepository.findById(getRandomId(readerIds)).get(), LocalDate.now(), null), 0);
        borrowingEntryRepository.insert(BorrowingEntry.of(0, bookRepository.findById(getUniqueRandomId(bookIds)).get(), readerRepository.findById(getRandomId(readerIds)).get(), LocalDate.now(), null), 0);
        borrowingEntryRepository.insert(BorrowingEntry.of(0, bookRepository.findById(getUniqueRandomId(bookIds)).get(), readerRepository.findById(getRandomId(readerIds)).get(), LocalDate.now().minusDays(19), LocalDate.now().minusDays(10)), 0);
        borrowingEntryRepository.insert(BorrowingEntry.of(0, bookRepository.findById(getUniqueRandomId(bookIds)).get(), readerRepository.findById(getRandomId(readerIds)).get(), LocalDate.now().minusDays(12), LocalDate.now().minusDays(1)), 0);
        borrowingEntryRepository.insert(BorrowingEntry.of(0, bookRepository.findById(getUniqueRandomId(bookIds)).get(), readerRepository.findById(getRandomId(readerIds)).get(), LocalDate.now().minusDays(30), LocalDate.now().minusDays(5)), 0);
    }

    private long getRandomId(Set<Long> ids, String seed) {
        return ids.stream().skip(Math.abs(seed.hashCode()) % ids.size()).findFirst().get();
    }

    private long getRandomId(Set<Long> ids) {
        return ids.stream().skip((int) (ids.size() * Math.random())).findFirst().get();
    }

    private long getUniqueRandomId(Set<Long> ids) {
        long res = ids.stream().skip((int) (ids.size() * Math.random())).findFirst().get();
        ids.remove(res);
        return res;
    }

}
