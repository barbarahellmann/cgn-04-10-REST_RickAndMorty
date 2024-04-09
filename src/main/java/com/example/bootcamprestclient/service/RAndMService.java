package com.example.bootcamprestclient.service;

import com.example.bootcamprestclient.model.Character;
import com.example.bootcamprestclient.model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RAndMService {

    private RestClient restClient;

    public RAndMService(@Value("${RICK_URL}") String baseURL){
        restClient = RestClient.builder()
                .baseUrl(baseURL)
                .build();
    }
    public List<Character> getAllCharacters() {
        return restClient.get().uri("/character").retrieve().body(Response.class).getResults();
    }

    public Character getCharacterById(int id) {
        return restClient.get().uri("/character/" + id).retrieve().body(Character.class);
    }

    public List<Character> getCharacterWithStatus(String status) {
        String statusCap = status.substring(0,1).toUpperCase() + status.substring(1);
        return getAllCharacters().stream()
                .filter(character -> character.getStatus().equals(statusCap))
                .collect(Collectors.toList());
    }

    public long getSumOfSpecies(String species) {
        String speciesCap = species.substring(0,1).toUpperCase() + species.substring(1);
        return getAllCharacters().stream()
                .filter(character -> character.getSpecies().equals(speciesCap) && character.getStatus().equals("Alive"))
                .count();
    }
}
