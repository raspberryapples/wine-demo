package com.spring.rest.model;

public class Component {

    private String percentage;
    private String year;
    private String variety;
    private String region;


    public Component(String percentage, String year, String variety, String region) {
        super();
        this.percentage = percentage;
        this.year = year;
        this.variety = variety;
        this.region = region;
    }

    public String getYear() {
        return year;
    }

    public String getVariety() {
        return variety;
    }

    public String getRegion() {
        return region;
    }

    public String getPercentage() {
        return percentage;
    }


}




