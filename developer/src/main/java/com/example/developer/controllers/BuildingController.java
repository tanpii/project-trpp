package com.example.developer.controllers;

import com.example.developer.models.Apartment;
import com.example.developer.models.Building;
import com.example.developer.models.DistanceRange;
import com.example.developer.services.ApartmentService;
import com.example.developer.services.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    // список всех ЖК
    @GetMapping
    public List<Building> findAll() {
        return buildingService.findAll(true);
    }
    // поиск по параметру в http-запросе по названию, начинающегося с prefix
    @GetMapping("")
    public List<Building> findTitleStartsWith(@RequestParam String prefix) {
        return buildingService.findByTitleStartsWith(prefix);
    }

    //список всех жк с дистанцией между двумя числами
    @PostMapping("/distance")
    public List<Building> findBetween(@RequestBody DistanceRange distanceRange) {
        int min = distanceRange.getMin();
        int max = distanceRange.getMax();
        return buildingService.findBetweenDistance(min, max);
    }
    @GetMapping("{title}/apartments")
    public List<Apartment> getAparts(@PathVariable("title") String title) throws Exception {
        return buildingService.getApartmentsByBuildingTitle(title);
    }
}
