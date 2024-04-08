package com.example.bootcamprestclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Response {

    private List<Character> results;

}
