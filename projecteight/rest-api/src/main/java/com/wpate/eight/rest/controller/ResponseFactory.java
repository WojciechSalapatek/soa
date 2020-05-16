package com.wpate.eight.rest.controller;

import javax.ws.rs.core.Response;
import java.net.URI;

public class ResponseFactory {

    public static Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public static Response noContent() {
        return Response.noContent().build();
    }

    public static Response created() {
        return Response.status(Response.Status.CREATED).build();
    }

    public static Response seeOther(String uri) {
        return Response.seeOther(URI.create(uri)).build();
    }

    public static Response ok(Object o) {
        return Response.ok(o).build();
    }

}
