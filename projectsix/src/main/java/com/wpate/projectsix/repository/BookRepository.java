package com.wpate.projectsix.repository;

import com.wpate.projectsix.domain.Book;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.OptimisticLockException;

@Stateless
public class BookRepository extends GenericCrudRepository<Book> {

    @Getter
    private final String selectAllQuery = "SELECT b FROM Book b";

    private final String selectIsBorrowedQuery = "SELECT count(e) FROM BorrowingEntry e WHERE e.book.id = :bookId AND e.returnDate is null";
    private final String selectNBorrowedBooks = "SELECT count(e) FROM BorrowingEntry e WHERE e.book.title = :bookTitle AND e.returnDate is null";

    public BookRepository() {
        super(Book.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean isBorrowed(long id) {
        return manager.createQuery(selectIsBorrowedQuery, Long.class).setParameter("bookId", id).getSingleResult() > 0;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int borrowedBooks(String title) {
        return manager.createQuery(selectNBorrowedBooks, Long.class).setParameter("bookTitle", title).getSingleResult().intValue();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(Book entity, Book newData) {
        entity.setTitle(newData.getTitle());
        entity.setAuthor(newData.getAuthor());
        entity.setCategory(newData.getCategory());
        entity.setIsbn(newData.getIsbn());
        try {
            manager.merge(entity);
            return true;
        } catch (OptimisticLockException e){
            return false;
        }
    }

}
