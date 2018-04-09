package com.example.darya.coffeeshop;

/**
 * Created by darya on 09.04.18.
 */

public class Coffee {

    private String name;
    private String description;
    private int imageResourceId;

    private Coffee(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public  static final Coffee[] coffee = {
            new Coffee("Latte", "Espresso with milk", R.drawable.ic_action_latte),
            new Coffee("Dark", "Espresso, milk, foam", R.drawable.ic_action_dark),
            new Coffee("Cappuccino", "Espresso, chocolate, milk, foam", R.drawable.ic_action_cappuchino)
    };
}
