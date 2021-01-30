package com.spring.rest.model;

public class WineSearch {
    String lotCode;
    String description;

    public WineSearch(String lotCode, String description) {
        this.lotCode = lotCode;
        this.description = description;

    }

    public String getLotCode() {
        return lotCode;
    }

    public String getDescription() {
        return description;
    }
}

