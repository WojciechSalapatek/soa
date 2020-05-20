package com.wpate.eight.client.rest;

import lombok.SneakyThrows;

import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

public class ResponseUtils {

    @SneakyThrows
    public static Response checkResponse(Response response) {
        if (Response.Status.Family.SUCCESSFUL != response.getStatusInfo().getFamily()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
            return null;
        }
        return response;
    }

}
