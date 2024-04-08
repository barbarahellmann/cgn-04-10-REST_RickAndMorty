package com.example.bootcamprestclient.controller;

import com.example.bootcamprestclient.model.Character;
import com.example.bootcamprestclient.service.RAndMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RAndMController {

    private final RAndMService service;

    @GetMapping("/character")
    private List<Character> getAllCharacters() {
        return service.getAllCharacters();
    }
}
