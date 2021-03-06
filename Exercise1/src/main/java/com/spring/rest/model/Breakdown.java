package com.spring.rest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Breakdown {

    private String breakDownType;
    public List<BreakdownInner> breakdown = new ArrayList<>();

    public String getBreakDownType() {
        return breakDownType;
    }

    public List<BreakdownInner> getBreakdown() {
        return breakdown;
    }
    public Breakdown(String breakDownType) {
        this.breakDownType = breakDownType;
    }

    public void sort() {
        Collections.sort(breakdown);
    }

    private void add(String percentage, String key) {
        breakdown.add(new BreakdownInner(percentage, key));
    }

    // A more efficient hasKey function could be created, since data required is small a simplistic one was made.
    public boolean hasKey(String key) {
        for (BreakdownInner inner: breakdown) {
            if (inner.getKey().equals(key)) return true;
        }
        return false;
    }

    // A more efficient updatePercentage function could be created, since data required is small a simplistic one was made.
    private void updatePercentage(String percentage, String key) {
        for (BreakdownInner inner: breakdown) {
            if (inner.getKey().equals(key)) {
                double value = Double.parseDouble(percentage) + Double.parseDouble(inner.getPercentage());
                inner.setPercentage(Double.toString(value));
                return;

            }
        }
    }

    public void addKey(String key, String percentage) {
        if (hasKey(key)) {
            updatePercentage(percentage, key);
        } else {
            add(percentage, key);
        }
    };

}

// Made it implement comparable for easy sorting
class BreakdownInner implements Comparable<BreakdownInner>{
    private String percentage;
    private String key;

    public String getPercentage() {
        return percentage;
    }
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getKey() {
        return key;
    }

    public BreakdownInner(String percentage, String key) {
        this.percentage = percentage;
        this.key = key;
    }

    @Override
    public int compareTo(BreakdownInner o) {
        return (int)(Double.parseDouble(o.percentage) - Double.parseDouble(this.percentage));
    }
}
