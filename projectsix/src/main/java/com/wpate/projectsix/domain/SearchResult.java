package com.wpate.projectsix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchResult {

    private Book book;
    private BookAuthor author;
    private BorrowingEntry borrowingEntry;
    private Reader reader;

}
