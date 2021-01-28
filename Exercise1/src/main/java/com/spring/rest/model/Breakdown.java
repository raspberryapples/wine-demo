package com.spring.rest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Breakdown {

    public String getBreakDownType() {
        return breakDownType;
    }

    public List<BreakdownInner> getBreakdown() {
        return breakdown;
    }

    private String breakDownType;

    public List<BreakdownInner> breakdown = new ArrayList<>();

    public Breakdown(String breakDownType) {
        this.breakDownType = breakDownType;
    }

    public void sort() {
        Collections.sort(breakdown);
    }

    public void add(String percentage, String key) {
        breakdown.add(new BreakdownInner(percentage, key));
    }

    // A more efficient hasKey function could be created, since data required is small a simplistic one was made.
    public boolean hasKey(String key) {
        for (BreakdownInner inner: breakdown) {
            if (inner.getKey().equals(key)) return true;
        }
        return false;
    }

    public void updatePercentage(String key, String percentage) {
        for (BreakdownInner inner: breakdown) {
            if (inner.getKey().equals(key)) {
                double value = Double.parseDouble(percentage) + Double.parseDouble(inner.getPercentage());
                inner.setPercentage(Double.toString(value));
            }
            return;
        }
    }

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
