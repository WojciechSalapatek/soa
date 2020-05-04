package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.BookCategory;
import com.wpate.projectsix.domain.SearchFilter;
import com.wpate.projectsix.domain.SearchResult;
import com.wpate.projectsix.service.SearchResultProvider;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SessionScoped
@Named("SearchView")
public class SearchView implements Serializable {

    @Inject
    private SearchResultProvider searchResultProvider;

    private String title;
    private String authorName;
    private String readerName;
    private String readerSurname;
    private String isbn;
    private String bookCategory;
    private String borrowedFrom;
    private String borrowedTo;

    private boolean hasTitle;
    private boolean hasAuthorName;
    private boolean hasReaderName;
    private boolean hasReaderSurname;
    private boolean hasIsbn;
    private boolean hasBookCategory;
    private boolean hasBorrowedFrom;
    private boolean hasBorrowedTo;

    private boolean hasResult;
    private List<SearchResult> results;

    public String search(){
        this.results = searchResultProvider.getSearchResult(new SearchFilter(title, authorName, readerName, readerSurname, isbn, bookCategory, borrowedFrom, borrowedTo));
        this.hasResult = true;
        return "index";
    }
}
