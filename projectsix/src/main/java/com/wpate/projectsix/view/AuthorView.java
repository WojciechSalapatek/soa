package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.BookAuthor;
import com.wpate.projectsix.repository.AuthorRepository;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SessionScoped
@Named("AuthorView")
public class AuthorView implements Serializable {

    @Inject
    private AuthorRepository authorRepository;

    private String newName;

    public List<BookAuthor> getAllAuthors() {
        return authorRepository.findAll();
    }

    public String delete(long authorId) {
        authorRepository.deleteById(authorId);
        return "index";
    }

    public String addAuthor() {
        authorRepository.insert(BookAuthor.builder().name(newName).build(), 0);
        return "index";
    }

}
