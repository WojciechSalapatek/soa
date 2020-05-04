package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.BorrowingEntry;
import com.wpate.projectsix.domain.Reader;
import com.wpate.projectsix.repository.ReaderRepository;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@SessionScoped
@Named("ReaderUpdateView")
public class ReaderUpdateView implements Serializable {

    private long id;
    private String name;
    private String surname;
    private Set<BorrowingEntry> borrowings;

    private Reader updatable;

    @Inject
    private ReaderRepository readerRepository;

    public String startUpdating(Reader reader) {
        this.id = reader.getId();
        this.name = reader.getName();
        this.surname = reader.getSurname();
        this.borrowings = reader.getBorrowings();
        updatable = readerRepository.startOptimisticUpdating(reader.getId());
        return "updateReader";
    }

    public String update() {
        return readerRepository.update(updatable, Reader.builder().name(name).surname(surname).borrowings(borrowings).build()) ? "index" : "updateError";
    }

}
