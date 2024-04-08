package com.example.bootcamprestclient.service;

import com.example.bootcamprestclient.model.Character;
import com.example.bootcamprestclient.model.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RAndMService {

    private RestClient restClient = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com/api")
            .build();


    public List<Character> getAllCharacters() {
        return restClient.get().uri("/character").retrieve().body(Response.class).getResults();
    }
}
