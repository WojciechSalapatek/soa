package com.wpate.projectsix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SearchFilter {

    private String title;
    private String authorName;
    private String readerName;
    private String readerSurname;
    private String isbn;
    private String bookCategory;
    private String borrowedFrom;
    private String borrowedTo;

}
