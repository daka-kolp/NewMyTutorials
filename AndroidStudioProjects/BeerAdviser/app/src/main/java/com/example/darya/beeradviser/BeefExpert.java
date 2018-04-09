package com.example.darya.beeradviser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darya on 24.03.18.
 */

public class BeefExpert {
    List<String> getBrands (String color) {
        List<String> brands = new ArrayList<String>();
        if(color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}
