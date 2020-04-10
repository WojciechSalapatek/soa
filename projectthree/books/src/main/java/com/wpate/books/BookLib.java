package com.wpate.books;


import com.wpate.books.model.Book;
import com.wpate.books.model.Currency;
import com.wpate.books.repository.BookInMemoryRepository;
import com.wpate.books.repository.BookRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wpate.books.model.Currency.ORIGINAL;
import static com.wpate.books.model.Currency.PLN;
import static com.wpate.books.service.CurrencyRecalculationService.recalculatePrice;

@Setter
@Getter
@SessionScoped
@Named("BookLib")
public class BookLib implements Serializable {

    private BookRepository repository = new BookInMemoryRepository();

    private boolean authorVisible = true;
    private boolean typeVisible = false;
    private boolean priceVisible = true;
    private boolean currencyVisible = true;
    private boolean pagesVisible = true;

    private Currency currency = ORIGINAL;
    private Currency finalCurrency = PLN;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<Book, Boolean> bookSelections = new HashMap<>();

    public List<Book> getBooks(){
        List<Book> allBooks = repository.findAll();
        if(currency != ORIGINAL) {
            allBooks.forEach(b -> {
                b.setPrice(recalculatePrice(b.getPrice(), b.getCurrency(), currency));
                b.setCurrency(currency);
            });
        }
        return allBooks;
    }

    public boolean getBookSelection(Book book) {
        if(!bookSelections.containsKey(book)){
            bookSelections.put(book, false);
        }
        return bookSelections.get(book);
    }

    public void flipSelection(Book book){
        bookSelections.put(book, !bookSelections.get(book));
    }

    public List<Book> getSelectedBooks(){
        return bookSelections.keySet().stream().filter(b -> bookSelections.get(b)).collect(Collectors.toList());
    }

    public String getSum(){
        finalCurrency = currency == ORIGINAL ? finalCurrency : currency;
        double sum = getSelectedBooks().stream().map(b -> recalculatePrice(b.getPrice(), b.getCurrency(), finalCurrency)).reduce(0.0, Double::sum);
        return String.format("%.2f", sum);
    }


}
