package com.example.workshop36_spring.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.workshop36_spring.models.City;

@Repository
public class CitiesRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_CITIES = "SELECT code,city_name FROM cities";

    public List<City> getAllCities() {
        return jdbcTemplate.query(SELECT_ALL_CITIES, (rs, rowNum) -> {
            return City.populate(rs);
        });
    }
    
}
