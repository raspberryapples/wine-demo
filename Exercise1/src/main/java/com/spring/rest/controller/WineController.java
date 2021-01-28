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
        System.out.println(wines.getWineList().size());

        // Since example is only 3 long no advanced searching algorithm was used.
        for (Wine wine : wines.getWineList()) {
            System.out.println(wine.getLotCode());
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




}
