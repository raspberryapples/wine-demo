package com.spring.rest.model;

public class Wine {

    private String lotCode;
    private String volume;
    private String description;
    private String tankCode;
    private String productState;
    private String ownerName;
    private Component[] components;

    public Wine(String lotCode, String volume, String description, String tankCode, String productState, String ownerName, Component[] components) {
        super();
        this.lotCode = lotCode;
        this.volume = volume;
        this.description = description;
        this.tankCode = tankCode;
        this.productState = productState;
        this.ownerName = ownerName;
        this.components = components;

    }

    public Wine() {
        this.components = new Component[0];
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

    public Component[] getComponents() {
        return components;
    }


}
