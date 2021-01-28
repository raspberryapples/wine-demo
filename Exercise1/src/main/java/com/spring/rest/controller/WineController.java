package com.spring.rest.controller;

import com.spring.rest.dao.WineDAO;
import com.spring.rest.model.Breakdown;
import com.spring.rest.model.Component;
import com.spring.rest.model.Wine;
import com.spring.rest.model.Wines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class WineController {

    @Autowired
    private WineDAO wineDAO;



    @GetMapping(path = "/api/breakdown/year/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForYear(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("year");
        Wines wines = wineDAO.getAllWines();

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                for (Component component: wine.getComponents()) {
                    if (breakdown.hasKey(component.getYear())) {
                        breakdown.updatePercentage(component.getPercentage(), component.getYear());
                    } else {
                        breakdown.add(component.getPercentage(), component.getYear());
                    }
                }
                break;
            }
        }

        breakdown.sort();

        return breakdown;
    }

    @GetMapping(path = "/api/breakdown/variety/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForVariety(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("variety");
        Wines wines = wineDAO.getAllWines();

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                for (Component component: wine.getComponents()) {
                    if (breakdown.hasKey(component.getVariety())) {
                        breakdown.updatePercentage(component.getPercentage(), component.getVariety());
                    } else {
                        breakdown.add(component.getPercentage(), component.getVariety());
                    }
                }
                break;
            }
        }

        breakdown.sort();

        return breakdown;
    }

    @GetMapping(path = "/api/breakdown/region/{lotCode}", produces =  "application/json")
    public Breakdown getPercentageForRegion(@PathVariable String lotCode) {
        Breakdown breakdown = new Breakdown("region");
        Wines wines = wineDAO.getAllWines();

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                for (Component component: wine.getComponents()) {
                    if (breakdown.hasKey(component.getRegion())) {
                        breakdown.updatePercentage(component.getPercentage(), component.getRegion());
                    } else {
                        breakdown.add(component.getPercentage(), component.getRegion());
                    }
                }
                break;
            }
        }

        breakdown.sort();

        return breakdown;
    }

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




}
