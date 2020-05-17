package com.wpate.eight.client.rest;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import java.io.Serializable;

import static com.wpate.eight.client.rest.ClientFactory.setupRestEasyClient;

@Slf4j
@Stateless
public class PersonRestClient implements Serializable {

    @Getter
    private PersonApi personApi;

    public PersonRestClient() {
        personApi = setupRestEasyClient(PersonApi.class);
    }

}
