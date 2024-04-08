package com.example.bootcamprestclient.service;

import com.example.bootcamprestclient.model.Character;
import com.example.bootcamprestclient.model.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RAndMService {

    private RestClient restClient = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com/api")
            .build();


    public List<Character> getAllCharacters() {
        return restClient.get().uri("/character").retrieve().body(Response.class).getResults();
    }

    public Character getCharacterById(int id) {
        return getAllCharacters().stream()
                .filter(character -> character.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Character> getCharacterWithStatus(String status) {
        String statusCap = status.substring(0,1).toUpperCase() + status.substring(1);
        return getAllCharacters().stream()
                .filter(character -> character.getStatus().equals(statusCap))
                .collect(Collectors.toList());
    }
}
