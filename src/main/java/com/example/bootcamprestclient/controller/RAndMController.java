package com.example.bootcamprestclient.controller;

import com.example.bootcamprestclient.model.Character;
import com.example.bootcamprestclient.service.RAndMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RAndMController {

    private final RAndMService service;

    @GetMapping("/characters")
    private List<Character> getCharacters(@RequestParam(name = "status", required = false) String status) {
        if (status == null) {
            return service.getAllCharacters();
        } else {
            return service.getCharacterWithStatus(status);
        }
    }

    @GetMapping("/characters/{id}")
    private Character getCharacterById(@PathVariable int id) {
        return service.getCharacterById(id);
    }

    @GetMapping("/species-statistic")
    private long getSumOfSpecies(@RequestParam("species") String species) {
        return service.getSumOfSpecies(species);
    }


}
