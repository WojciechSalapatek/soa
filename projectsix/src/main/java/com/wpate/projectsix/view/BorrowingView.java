package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.BorrowingEntry;
import com.wpate.projectsix.repository.BorrowingEntryRepository;
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
@Named("BorrowingView")
public class BorrowingView implements Serializable {

    @Inject
    private BorrowingEntryRepository borrowingEntryRepository;

    public List<BorrowingEntry> getAllBorrowings() {
        return borrowingEntryRepository.findAll();
    }

    public String markAsReturned(long id) {
        borrowingEntryRepository.markAsReturned(id);
        return "index";
    }

}
