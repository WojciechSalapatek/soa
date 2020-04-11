package com.wpate.projectfive.client;

import com.wpate.projectfive.model.Book;
import com.wpate.projectfive.model.Currency;
import com.wpate.projectfive.repository.BookRepository;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Setter
@Getter
@RequestScoped
@Named("UpdateForm")
public class UpdateForm {

    private long updateId;
    private String updateTitle;
    private String updateAuthor;
    private String updateIsbn;
    private int updateYear;
    private double updatePrice;
    private Currency updateCurrency;

    @EJB
    private BookRepository bookRepository;

    public String startUpdating(Book book){
        updateId = book.getId();
        updateTitle = book.getTitle();
        updateAuthor = book.getAuthor();
        updateIsbn = book.getIsbn();
        updateYear = book.getYear();
        updatePrice = book.getPrice();
        updateCurrency = book.getCurrency();
        return "updateForm";
    }

    public String updateBook(){
        bookRepository.update(Book.of(updateId, updateTitle, updateAuthor, updateYear, updateIsbn, updatePrice, updateCurrency));
        return "index";
    }

}
