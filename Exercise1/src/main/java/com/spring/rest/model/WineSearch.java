package com.spring.rest.model;

public class WineSearch {
    String lotCode;
    String description;
    private String volume;
    private String tankCode;

    public WineSearch(String lotCode, String description, String volume, String tankCode) {
        this.lotCode = lotCode;
        this.description = description;
        this.volume = volume;
        this.tankCode = tankCode;
    }

    public String getVolume() {
        return volume;
    }

    public String getTankCode() {
        return tankCode;
    }

    public String getLotCode() {
        return lotCode;
    }

    public String getDescription() {
        return description;
    }
}

