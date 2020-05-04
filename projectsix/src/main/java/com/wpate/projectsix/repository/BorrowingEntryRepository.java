package com.wpate.projectsix.repository;

import com.wpate.projectsix.domain.BorrowingEntry;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Objects.isNull;

@Stateless
public class BorrowingEntryRepository extends GenericCrudRepository<BorrowingEntry> {

    @Getter
    private final String selectAllQuery = "SELECT e FROM BorrowingEntry e";

    public BorrowingEntryRepository() {
        super(BorrowingEntry.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void markAsReturned(long id) {
        Optional<BorrowingEntry> entry = findById(id);
        if (entry.isEmpty()) {
            throw new RuntimeException("Could not find entry with such id:" + id);
        }
        if (!isNull(entry.get().getReturnDate())) {
            throw new RuntimeException("Cant mark as returned already returned position!");
        }
        BorrowingEntry e = entry.get();
        e.setReturnDate(LocalDate.now());
        insert(e, e.getId());
    }


}
