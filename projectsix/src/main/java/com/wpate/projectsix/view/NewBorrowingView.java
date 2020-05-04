package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.Book;
import com.wpate.projectsix.domain.BorrowingEntry;
import com.wpate.projectsix.repository.BookRepository;
import com.wpate.projectsix.repository.BorrowingEntryRepository;
import com.wpate.projectsix.repository.ReaderRepository;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@SessionScoped
@Named("NewBorrowingView")
public class NewBorrowingView implements Serializable {

    private Book book;
    private long readerId;
    boolean finalized;

    @Inject
    private BorrowingEntryRepository borrowingEntryRepository;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private ReaderRepository readerRepository;

    public String startBorrowing(long bookId) {
        book = bookRepository.pessimisticLock(bookId);
        return "newBorrowing";
    }

    public String borrow() {
        borrowingEntryRepository.insert(BorrowingEntry.of(0, book, readerRepository.findById(readerId).get(), LocalDate.now(), null), 0);
        bookRepository.update(book, book);
        finalized = true;
        return "index";
    }

    @PreDestroy
    public void release() {
        if (!finalized) {
            bookRepository.update(book, book);
        }
    }
}
