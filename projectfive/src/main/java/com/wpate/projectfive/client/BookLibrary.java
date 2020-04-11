package com.wpate.projectfive.client;


import com.wpate.projectfive.model.Book;
import com.wpate.projectfive.model.Currency;
import com.wpate.projectfive.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@RequestScoped
@Named("BookLibrary")
public class BookLibrary implements Serializable {

    @EJB
    private BookRepository bookRepository;

    private String newTitle;
    private String newAuthor;;
    private String newIsbn;
    private int newYear;
    private double newPrice;
    private Currency newCurrency;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public String delete(long bookId){
        bookRepository.deleteById(bookId);
        return "index";
    }

    public String addBook(){
        bookRepository.update(Book.of(0, newTitle, newAuthor, newYear, newIsbn, newPrice, newCurrency));
        return "index";
    }

}
