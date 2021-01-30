package com.spring.rest.model;

import org.springframework.lang.Nullable;

public class WineDetails {


    private String lotCode;
    private String volume;
    private String description;
    private String tankCode;
    private String productState;
    private String ownerName;

    public WineDetails(String lotCode, @Nullable String volume, @Nullable String description, @Nullable String tankCode, @Nullable String productState, @Nullable String ownerName) {
        super();
        this.lotCode = lotCode;
        this.volume = volume;
        this.description = description;
        this.tankCode = tankCode;
        this.productState = productState;
        this.ownerName = ownerName;
    }

    public String getLotCode() {
        return lotCode;
    }

    public String getVolume() {
        return volume;
    }

    public String getDescription() {
        return description;
    }

    public String getTankCode() {
        return tankCode;
    }

    public String getProductState() {
        return productState;
    }

    public String getOwnerName() {
        return ownerName;
    }



}
