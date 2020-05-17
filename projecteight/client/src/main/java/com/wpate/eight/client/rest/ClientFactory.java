package com.wpate.eight.client.rest;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.UriBuilder;

import static com.wpate.eight.client.rest.Config.LOCAL_PATH;

public class ClientFactory {

    public static <T> T setupRestEasyClient(Class<T> clazz) {
        ResteasyClient client = new ResteasyClientBuilder().connectionPoolSize(20).build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(LOCAL_PATH));
        return target.proxy(clazz);
    }

}
