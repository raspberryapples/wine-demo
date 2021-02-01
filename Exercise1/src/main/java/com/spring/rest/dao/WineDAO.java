package com.spring.rest.dao;

import com.google.gson.Gson;
import com.spring.rest.model.Wine;
import com.spring.rest.model.Wines;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;


@Repository
public class WineDAO {

    private static Wines wines = new Wines();

    // this static method adds each of the JSON files within the Wines folder in resources into the database
    static {

        try {
            URL url = WineDAO.class.getClassLoader().getResource("Wines");
            if (url == null) {
                throw new IllegalArgumentException("Wines folder not found!");
            } else {
                File folder = new File(url.toURI());

                for (File file : folder.listFiles()) {
                    Wine wineData = new Gson().fromJson(new String(Files.readAllBytes(file.toPath()), "UTF-8"), Wine.class);
                    System.out.println("Extracting data from file: " + file.getName());
                    wines.getWineList().add(wineData);
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }


    public Wines getAllWines() {
        return wines;
    }

    public Wine getWine(String lotCode) {

        Wines wineList = getAllWines();
        for (Wine wine: wineList.getWineList()) {
            if (wine.getLotCode().equals(lotCode)) {
                return wine;
            }
        }

        return (new Wine());

    }





}
