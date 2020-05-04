package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.Book;
import com.wpate.projectsix.domain.BookCategory;
import com.wpate.projectsix.repository.AuthorRepository;
import com.wpate.projectsix.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Getter
@Setter
@SessionScoped
@Named("BookUpdateView")
public class BookUpdateView implements Serializable {

    private long id;
    private String title;
    private long authorId;
    private String isbn;
    private BookCategory category;

    private Book updatable;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private AuthorRepository authorRepository;

    public String startUpdating(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.authorId = book.getAuthor().getId();
        this.category = book.getCategory();
        updatable = bookRepository.startOptimisticUpdating(book.getId());
        return "updateBook";
    }

    public String update() {
        return bookRepository.update(updatable, Book.builder().title(title).category(category).isbn(isbn).author(authorRepository.findById(authorId).get()).build()) ? "index" : "updateError";
    }

}
