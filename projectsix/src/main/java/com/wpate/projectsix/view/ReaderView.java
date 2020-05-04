package com.wpate.projectsix.view;

import com.wpate.projectsix.domain.Reader;
import com.wpate.projectsix.repository.ReaderRepository;
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
@Named("ReaderView")
public class ReaderView  implements Serializable {

    private String newName;
    private String newSurname;

    @Inject
    private ReaderRepository readerRepository;

    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }

    public String delete(long id){
        readerRepository.deleteById(id);
        return "index";
    }

    public String addReader(){
        readerRepository.insert(Reader.builder().name(newName).surname( newSurname).build(), 0);
        return "index";
    }

}
