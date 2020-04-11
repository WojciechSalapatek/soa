package com.wpate.projectfive.repository;

import com.wpate.projectfive.model.Book;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.wpate.projectfive.model.Currency.PLN;
import static com.wpate.projectfive.model.Currency.USD;
import static java.util.Optional.ofNullable;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
@Local(BookRepository.class)
public class BookDbRepository implements BookRepository {

    @PersistenceContext(unitName = "books")
    private EntityManager manager;

    private static final String SELECT_ALL_QUERY = "SELECT b FROM Book b";

    @Override
    @TransactionAttribute(REQUIRED)
    public List<Book> findAll() {
        return manager.createQuery(SELECT_ALL_QUERY, Book.class).getResultList();
    }

    @Override
    @TransactionAttribute(REQUIRED)
    public Optional<Book> findById(long id) {
        Book b = manager.find(Book.class, id);
        return ofNullable(b);
    }

    @Override
    @TransactionAttribute(REQUIRED)
    public void update(Book book) {
        if(book.getId() == 0){
            manager.persist(book);
            return;
        }
        Optional<Book> bookToUpdate = findById(book.getId());
        if (bookToUpdate.isEmpty()){
            manager.persist(book);
        } else {
            manager.merge(book);
        }
    }


    @Override
    @TransactionAttribute(REQUIRED)
    public boolean deleteById(long id) {
        Optional<Book> bookToRemove = findById(id);
        if(bookToRemove.isEmpty()) return false;
        manager.remove(bookToRemove.get());
        return true;
    }

    @PostConstruct
    @TransactionAttribute(REQUIRES_NEW)
    void setupSomeBooks(){
        Book b1 = new Book();
        b1.setTitle("Title");
        b1.setAuthor("Wpate");
        b1.setYear(1997);
        b1.setIsbn("978-83-01-01300-1");
        b1.setPrice(9.99);
        b1.setCurrency(PLN);
        manager.persist(b1);
        Book b2 = new Book();
        b2.setTitle("Title2");
        b2.setAuthor("Sam Van");
        b2.setYear(1878);
        b2.setIsbn("221-33-01-01300-1");
        b2.setPrice(59.99);
        b2.setCurrency(USD);
        manager.persist(b2);
    }
}
