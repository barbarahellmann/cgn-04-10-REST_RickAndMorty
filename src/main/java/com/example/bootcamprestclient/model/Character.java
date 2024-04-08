package com.example.bootcamprestclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Character {

    private int id;
    private String name;
    private String species;
    private String status;

}
