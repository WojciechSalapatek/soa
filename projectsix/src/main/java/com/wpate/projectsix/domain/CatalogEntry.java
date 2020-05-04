package com.wpate.projectsix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CatalogEntry {

    private String title;
    private String author;
    private BookCategory category;
    private int all;
    private int available;

}
