package com.wpate.forms;


import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.regex.Pattern;


@Setter
@Getter
@SessionScoped
@Named("FormRoot")
public class FormRoot implements Serializable {

    private String email;
    private String name;
    private int age;
    private String sex = "male";
    private String education;
    private int height;

    private String breastSize;
    private String braCupSize;
    private String waist;
    private String hips;

    private String chest;
    private String waistSize;

    private String emailPattern = "^(.+)@(.+)$";


    public void validateEmail(FacesContext context, UIComponent comp,
                              Object value) {


        String email = (String) value;

        if (!email.matches(emailPattern)){
            ((UIInput) comp).setValid(false);

            FacesMessage message = new FacesMessage(
                    "Invalid email");
            context.addMessage(comp.getClientId(context), message);
        }

    }

    public void validateMaleHeight(FacesContext context, UIComponent comp,
                              Object value) {


        int height = (int) value;

        if (height > 200 || height < 165){
            ((UIInput) comp).setValid(false);

            FacesMessage message = new FacesMessage(
                    "Invalid height, height for male should be between 165-200");
            context.addMessage(comp.getClientId(context), message);
        }

    }

    public void validateFemaleHeight(FacesContext context, UIComponent comp,
                                   Object value) {


        int height = (int) value;

            if ((height > 185 || height < 150)){
            ((UIInput) comp).setValid(false);

            FacesMessage message = new FacesMessage(
                    "Invalid height, height for female should be between 150-185");
            context.addMessage(comp.getClientId(context), message);
        }

    }

}
