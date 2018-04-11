package com.example.darya.workout;

/**
 * Created by darya on 11.04.18.
 */

public class Workout {
    private String nameEx;
    private String description;

    public Workout(String name, String description) {
        this.nameEx = name;
        this.description = description;
    }

    public String getNameEx() {
        return nameEx;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.nameEx;
    }

    public static final Workout[] workouts = {
            new Workout("The Limb Loosener", "5 Handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
            new Workout("Core Agony", "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
            new Workout("The Wimp Special", "5 Pull-ups\n10 Push-ups\n15 Squats"),
            new Workout("Strength and Length", "500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
};


}
