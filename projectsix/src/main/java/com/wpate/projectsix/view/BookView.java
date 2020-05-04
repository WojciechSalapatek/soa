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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@SessionScoped
@Named("BookView")
public class BookView implements Serializable {

    private String newTitle;
    private long newAuthorId;
    private String newIsbn;
    private BookCategory newCategory;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<BookCategory> getAllCategories(){
        return Arrays.stream(BookCategory.values()).collect(Collectors.toList());
    }

    public String delete(long id){
        bookRepository.deleteById(id);
        return "index";
    }

    public boolean isBorrowed(long id){
        return bookRepository.isBorrowed(id);
    }

    public String addBook(){
        bookRepository.insert(Book.builder().title(newTitle).author(authorRepository.findById(newAuthorId).get()).isbn(newIsbn).category(newCategory).build(), 0);
        return "index";
    }

}
