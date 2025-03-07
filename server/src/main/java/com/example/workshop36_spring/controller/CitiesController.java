package com.example.workshop36_spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.workshop36_spring.models.City;
import com.example.workshop36_spring.service.CitiesService;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@Controller
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    @GetMapping(path = "/api/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCities() {

        JsonArray result = null;
        Optional<List<City>> cc = citiesService.getAllCities();

        JsonArrayBuilder jab = Json.createArrayBuilder();
        cc.get().forEach(c -> {
            jab.add(c.toJSON());
        });
        result = jab.build();
        return ResponseEntity.ok(result.toString());
    }
}
