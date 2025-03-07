package com.example.workshop36_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshop36_spring.models.City;
import com.example.workshop36_spring.repo.CitiesRepo;

@Service
public class CitiesService {

    @Autowired
    private CitiesRepo citiesRepo;

    public Optional<List<City>> getAllCities() {
        List<City> cc = citiesRepo.getAllCities();

        if (cc != null && !cc.isEmpty()) {
            return Optional.of(cc);
        }
        return Optional.empty();
    }

}
