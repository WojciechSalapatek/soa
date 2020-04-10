package com.wpate.forms;


import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;


@Setter
@Getter
@SessionScoped
@Named("AdditionalForm")
public class AdditionalForm implements Serializable {

    private String budget;
    private String frequency;
    private String colours;
    private String favourites;


}
