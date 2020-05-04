package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.CatalogEntry;
import com.wpate.projectsix.service.CatalogEntryProvider;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SessionScoped
@Named("CatalogView")
public class CatalogView implements Serializable {

    @Inject
    private CatalogEntryProvider catalogEntryProvider;


    public List<CatalogEntry> getAllEntries() {
        return catalogEntryProvider.getCatalogEntries();
    }

}
