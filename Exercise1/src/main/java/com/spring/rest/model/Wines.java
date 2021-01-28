package com.spring.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Wines {

    // An ArrayList was used as in the example it is only 3 long, as such no advanced sorting and searching algorithms need to be used.
    private List<Wine> wineList = new ArrayList<>();

    public List<Wine> getWineList() {
        return wineList;
    }


}
