package com.spring.rest.controller;

import com.spring.rest.dao.WineDAO;
import com.spring.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class WineController {

    @Autowired
    private WineDAO wineDAO;


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/api/breakdown/year/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForYear(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("year");
        Wines wines = wineDAO.getAllWines();

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                for (Component component: wine.getComponents()) {
                    String key = component.getYear();
                    if (breakdown.hasKey(key)) {
                        breakdown.updatePercentage(component.getPercentage(), key);
                    } else {
                        breakdown.add(component.getPercentage(), key);
                    }
                }
                break;
            }
        }

        breakdown.sort();

        return breakdown;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/api/breakdown/variety/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForVariety(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("variety");
        Wines wines = wineDAO.getAllWines();

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                for (Component component: wine.getComponents()) {
                    String key = component.getVariety();
                    if (breakdown.hasKey(key)) {
                        breakdown.updatePercentage(component.getPercentage(), key);
                    } else {
                        breakdown.add(component.getPercentage(), key);
                    }
                }
                break;
            }
        }

        breakdown.sort();

        return breakdown;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/api/breakdown/region/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForRegion(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("region");
        Wines wines = wineDAO.getAllWines();

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                for (Component component: wine.getComponents()) {
                    String key = component.getRegion();
                    if (breakdown.hasKey(key)) {
                        breakdown.updatePercentage(component.getPercentage(), key);
                    } else {
                        breakdown.add(component.getPercentage(), key);
                    }
                }
                break;
            }
        }

        breakdown.sort();

        return breakdown;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/api/breakdown/year-variety/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForYearVariety(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("year-variety");
        Wines wines = wineDAO.getAllWines();

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                for (Component component: wine.getComponents()) {
                    String key = component.getYear() + "-" + component.getVariety();
                    if (breakdown.hasKey(key)) {
                        breakdown.updatePercentage(component.getPercentage(), key);
                    } else {
                        breakdown.add(component.getPercentage(), key);
                    }
                }
                break;
            }
        }

        breakdown.sort();

        return breakdown;
    }



    @CrossOrigin(origins = "http://localhost:3000")
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/api/details/{lotCode}", produces = "application/json")
    public WineDetails getWineDetails(@PathVariable String lotCode) {
        Wines wines = wineDAO.getAllWines();
        for (Wine wine : wines.getWineList()) {
            if (lotCode.equals(wine.getLotCode())) {
                return new WineDetails(wine.getLotCode(), wine.getVolume(), wine.getDescription(), wine.getTankCode(), wine.getProductState(), wine.getOwnerName());
            }
        }
        return new WineDetails(null,null,null,null, null, null);

    }



}
