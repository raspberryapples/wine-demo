package com.spring.rest.controller;

import com.spring.rest.dao.WineDAO;
import com.spring.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class WineController {

    final String reactHost = "http://localhost:3000";

    @Autowired
    private WineDAO wineDAO;


    @CrossOrigin(origins = reactHost)
    @GetMapping(path = "/api/breakdown/year/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForYear(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("year");
        Wine wine = wineDAO.getWine(lotCode);

        for (Component component: wine.getComponents()) {
            breakdown.addKey(component.getYear(), component.getPercentage());
        }
        breakdown.sort();

        return breakdown;
    }

    @CrossOrigin(origins = reactHost)
    @GetMapping(path = "/api/breakdown/variety/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForVariety(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("variety");
        Wine wine = wineDAO.getWine(lotCode);

        for (Component component: wine.getComponents()) {
            breakdown.addKey(component.getVariety(), component.getPercentage());
        }
        breakdown.sort();

        return breakdown;
    }

    @CrossOrigin(origins = reactHost)
    @GetMapping(path = "/api/breakdown/region/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForRegion(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("region");
        Wine wine = wineDAO.getWine(lotCode);

        for (Component component: wine.getComponents()) {
            breakdown.addKey(component.getRegion(), component.getPercentage());
        }
        breakdown.sort();

        return breakdown;
    }

    @CrossOrigin(origins = reactHost)
    @GetMapping(path = "/api/breakdown/year-variety/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForYearVariety(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("year-variety");
        Wine wine = wineDAO.getWine(lotCode);

        for (Component component: wine.getComponents()) {
            breakdown.addKey(component.getYear()+"-"+component.getVariety(), component.getPercentage());
        }
        breakdown.sort();

        return breakdown;
    }



    @CrossOrigin(origins = reactHost)
    @GetMapping(path = "/api/search/{text}", produces = "application/json")
    public List<WineSearch> searchWine(@PathVariable String text) {

        Wines wines = wineDAO.getAllWines();
        List<WineSearch> wineSearch = new ArrayList<>();

        for (Wine wine : wines.getWineList()) {

            String lotCode = wine.getLotCode();
            String description = wine.getDescription();

            if (description == null) {
                description = "";
            }
            String volume = wine.getVolume();
            String tankCode = wine.getTankCode();

            if (lotCode.startsWith(text) || description.startsWith(text)) {
                wineSearch.add(new WineSearch(lotCode, description, volume, tankCode));
            }

        }
        return wineSearch;
    }

    @CrossOrigin(origins = reactHost)
    @GetMapping(path = "/api/details/{lotCode}", produces = "application/json")
    public WineDetails getWineDetails(@PathVariable String lotCode) {
        Wine wine = wineDAO.getWine(lotCode);
        return new WineDetails(wine.getLotCode(), wine.getVolume(), wine.getDescription(), wine.getTankCode(), wine.getProductState(), wine.getOwnerName());
    }



}
