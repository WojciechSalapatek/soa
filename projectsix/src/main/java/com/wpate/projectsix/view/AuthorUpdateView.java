package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.BookAuthor;
import com.wpate.projectsix.repository.AuthorRepository;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Getter
@Setter
@SessionScoped
@Named("AuthorUpdateView")
public class AuthorUpdateView implements Serializable {

    private long id;
    private String name;

    private BookAuthor updatable;

    @Inject
    private AuthorRepository authorRepository;

    public String startUpdating(BookAuthor author) {
        this.id = author.getId();
        this.name = author.getName();
        updatable = authorRepository.startOptimisticUpdating(author.getId());
        return "updateAuthor";
    }

    public String update() {
        return authorRepository.update(updatable, BookAuthor.builder().name(name).build()) ? "index" : "updateError";
    }

}
