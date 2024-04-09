package com.example.bootcamprestclient.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RAndMServiceTest {

    @Autowired
    private MockMvc mvc;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void shutDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("RICK_URL", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getRickAndMortyCharById_shouldReturnRickSanchez_WhenCalledWithId1() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("""
                         {
                                                     "id": 1,
                                                     "name": "Rick Sanchez",
                                                     "status": "Alive",
                                                     "species": "Human",
                                                     "type": "",
                                                     "gender": "Male",
                                                     "origin": {
                                                         "name": "Earth (C-137)",
                                                         "url": "https://rickandmortyapi.com/api/location/1"
                                                     }
                                                 }
                        """));

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/characters/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "id": 1,
                            "name" : "Rick Sanchez",
                            "species" : "Human",
                            "status" : "Alive"
                        }
                        """));

    }



}